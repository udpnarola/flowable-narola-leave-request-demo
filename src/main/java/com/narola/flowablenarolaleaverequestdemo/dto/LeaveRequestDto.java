package com.narola.flowablenarolaleaverequestdemo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class LeaveRequestDto {

    @NotNull(message = "Employee id is mandatory")
    private Long empId;
    @NotNull(message = "Leave day(s) are mandatory")
    private Integer days;
    private String reason;
    @NotNull(message = "Department is mandatory")
    private String department;

}
