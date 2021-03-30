package com.narola.flowablenarolaleaverequestdemo.service;

import com.narola.flowablenarolaleaverequestdemo.dto.LeaveDecisionDto;
import com.narola.flowablenarolaleaverequestdemo.dto.LeaveRequestDto;
import com.narola.flowablenarolaleaverequestdemo.dto.TaskDto;

import java.util.List;

public interface LeaveRequestService {

    void applyLeave(LeaveRequestDto leaveRequestDto);

    List<TaskDto> getDepartmentTasks(String department);

    void approveOrRejectLeave(LeaveDecisionDto leaveDecisionDto);
}
