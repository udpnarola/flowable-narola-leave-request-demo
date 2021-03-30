package com.narola.flowablenarolaleaverequestdemo.controller;

import com.narola.flowablenarolaleaverequestdemo.dto.EmployeeDto;
import com.narola.flowablenarolaleaverequestdemo.mapper.EmployeeMapper;
import com.narola.flowablenarolaleaverequestdemo.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;
    private final EmployeeMapper employeeMapper;

    @PostMapping
    public EmployeeDto save(@RequestBody EmployeeDto employeeDto) {
        return employeeMapper.toDto(employeeService.save(employeeDto));
    }

    @PutMapping
    public EmployeeDto update(@RequestBody EmployeeDto employeeDto) {
        return employeeMapper.toDto(employeeService.update(employeeDto));
    }

    @GetMapping("{id}")
    public EmployeeDto getById(@PathVariable Long id) {
        return employeeMapper.toDto(employeeService.getById(id));
    }

    @GetMapping
    public List<EmployeeDto> getAll() {
        return employeeMapper.toDtos(employeeService.getAll());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        employeeService.deleteById(id);
        return ResponseEntity.ok("User deleted successfully");
    }

}
