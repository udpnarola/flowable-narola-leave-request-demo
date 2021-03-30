package com.narola.flowablenarolaleaverequestdemo.service;

import com.narola.flowablenarolaleaverequestdemo.dto.EmployeeDto;
import com.narola.flowablenarolaleaverequestdemo.entity.EmployeeEntity;

import java.util.List;

public interface EmployeeService {

    EmployeeEntity save(EmployeeDto employeeDto);

    EmployeeEntity update(EmployeeDto employeeDto);

    EmployeeEntity getById(Long id);

    List<EmployeeEntity> getAll();

    void deleteById(Long id);

    boolean isExistsById(Long id);

    boolean isExistsByEmail(String email);

    EmployeeEntity getByEmail(String email);

    void checkEmployeeExistsById(Long id);
}
