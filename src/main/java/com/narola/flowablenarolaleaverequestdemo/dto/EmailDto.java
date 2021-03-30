package com.narola.flowablenarolaleaverequestdemo.dto;

import com.narola.flowablenarolaleaverequestdemo.entity.EmployeeEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EmailDto {

    private EmployeeEntity requestedBy;
    private EmployeeEntity decisionBy;
    private Integer days;
    private String reason;
    private Boolean isApproved;
}
