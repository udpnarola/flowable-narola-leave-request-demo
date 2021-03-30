package com.narola.flowablenarolaleaverequestdemo.service.impl;

import com.narola.flowablenarolaleaverequestdemo.dto.EmployeeDto;
import com.narola.flowablenarolaleaverequestdemo.entity.EmployeeEntity;
import com.narola.flowablenarolaleaverequestdemo.mapper.EmployeeMapper;
import com.narola.flowablenarolaleaverequestdemo.repository.EmployeeRepository;
import com.narola.flowablenarolaleaverequestdemo.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.narola.flowablenarolaleaverequestdemo.constant.Constants.*;
import static com.narola.flowablenarolaleaverequestdemo.util.ExceptionUtil.*;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    @Override
    public EmployeeEntity save(EmployeeDto employeeDto) {
        validateUserBeforeSave(employeeDto);
        return employeeRepository.save(employeeMapper.toEntity(employeeDto));
    }

    @Override
    public EmployeeEntity update(EmployeeDto employeeDto) {
        validateUserBeforeUpdate(employeeDto);
        return employeeRepository.save(employeeMapper.toEntity(employeeDto));
    }

    @Override
    public EmployeeEntity getById(Long id) {
        return employeeRepository
                .findById(id)
                .orElseThrow(() -> notFoundException(ERR_EMP_NOT_FOUND + id));
    }

    @Override
    public List<EmployeeEntity> getAll() {
        return employeeRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        checkEmployeeExistsById(id);
        employeeRepository.deleteById(id);
    }

    @Override
    public boolean isExistsById(Long id) {
        return employeeRepository.existsById(id);
    }

    @Override
    public boolean isExistsByEmail(String email) {
        return employeeRepository.existsByEmail(email);
    }

    @Override
    public EmployeeEntity getByEmail(String email) {
        return employeeRepository
                .findByEmail(email)
                .orElseThrow(() -> notFoundException(ERR_EMP_NOT_FOUND_BY_EMAIL + email));
    }

    @Override
    public void checkEmployeeExistsById(Long id) {
        if (!isExistsById(id))
            throwNotFoundException(ERR_EMP_NOT_FOUND + id);
    }

    private void validateUserBeforeSave(EmployeeDto employeeDto) {
        if (employeeDto.getId() != null) {
            throwBadRequestException(ERR_EMP_ID_AVAILABLE);
        }
        checkEmailAlreadyExists(employeeDto);
    }

    private void validateUserBeforeUpdate(EmployeeDto employeeDto) {
        if (employeeDto.getId() == null)
            throwBadRequestException(ERR_EMP_ID_NOT_AVAILABLE);

        checkEmployeeExistsById(employeeDto.getId());

        if (!getByEmail(employeeDto.getEmail()).getId().equals(employeeDto.getId()))
            throwBadRequestException(ERR_EMP_EMAIL_AVAILABLE);
    }

    private void checkEmailAlreadyExists(EmployeeDto employeeDto) {
        if (isExistsByEmail(employeeDto.getEmail()))
            throwBadRequestException(ERR_EMP_EMAIL_AVAILABLE);
    }

}
