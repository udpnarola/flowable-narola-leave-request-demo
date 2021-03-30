package com.narola.flowablenarolaleaverequestdemo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskDto {

    private String taskId;
    private String taskName;
    private LeaveRequestDto leaveRequestDto;
}
