package com.narola.flowablenarolaleaverequestdemo.mapper;

import com.narola.flowablenarolaleaverequestdemo.dto.EmployeeDto;
import com.narola.flowablenarolaleaverequestdemo.entity.EmployeeEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    EmployeeEntity toEntity(EmployeeDto employeeDto);

    EmployeeDto toDto(EmployeeEntity employeeEntity);

    List<EmployeeDto> toDtos(List<EmployeeEntity> employeeEntities);
}
