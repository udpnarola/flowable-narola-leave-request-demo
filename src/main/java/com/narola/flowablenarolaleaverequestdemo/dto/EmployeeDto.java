package com.narola.flowablenarolaleaverequestdemo.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class EmployeeDto {

    private Long id;

    @NotBlank(message = "First name is mandatory")
    private String firstName;

    @NotBlank(message = "Last name is mandatory")
    private String lastName;

    @EqualsAndHashCode.Include
    @NotBlank(message = "Email name is mandatory")
    private String email;

    @EqualsAndHashCode.Include
    @NotBlank(message = "Department name is mandatory")
    private String department;
}
