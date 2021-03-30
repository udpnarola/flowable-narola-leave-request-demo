package com.narola.flowablenarolaleaverequestdemo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class LeaveDecisionDto {

    @NotNull(message = "isApproved can't be null")
    private Boolean isApproved;
    @NotNull(message = "approvedBy can't be null")
    private Long decisionBy;
    @NotNull(message = "taskId can't be null or empty")
    private String taskId;
}
