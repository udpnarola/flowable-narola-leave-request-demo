package com.narola.flowablenarolaleaverequestdemo.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.narola.flowablenarolaleaverequestdemo.dto.LeaveDecisionDto;
import com.narola.flowablenarolaleaverequestdemo.dto.LeaveRequestDto;
import com.narola.flowablenarolaleaverequestdemo.dto.TaskDto;
import com.narola.flowablenarolaleaverequestdemo.entity.EmployeeEntity;
import com.narola.flowablenarolaleaverequestdemo.service.EmployeeService;
import com.narola.flowablenarolaleaverequestdemo.service.LeaveRequestService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.task.api.Task;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.narola.flowablenarolaleaverequestdemo.constant.Constants.*;
import static com.narola.flowablenarolaleaverequestdemo.util.ExceptionUtil.throwBadRequestException;
import static com.narola.flowablenarolaleaverequestdemo.util.ExceptionUtil.throwNotFoundException;

@Service
@Log4j2
@RequiredArgsConstructor
public class LeaveRequestServiceImpl implements LeaveRequestService {

    private final EmployeeService employeeService;
    private final RuntimeService runtimeService;
    private final TaskService taskService;
    private final ObjectMapper objectMapper;

    @SneakyThrows
    @Override
    @Transactional
    public void applyLeave(LeaveRequestDto leaveRequestDto) {
        EmployeeEntity employee = employeeService.getById(leaveRequestDto.getEmpId());
        validateLeaveRequest(leaveRequestDto, employee);
        Map<String, Object> variables = new HashMap<>();
        variables.put(TL_APPROVAL_TASK_ASSIGNEE, employee.getDepartment());
        variables.put(LEAVE_REQUEST_DATA, objectMapper.writeValueAsString(leaveRequestDto));
        runtimeService.startProcessInstanceByKey(LEAVE_REQUEST_PROCESS_ID, variables);
    }

    @Override
    @Transactional
    public List<TaskDto> getDepartmentTasks(String department) {
        List<Task> tasks = taskService.createTaskQuery().taskAssignee(department).list();
        return tasks
                .parallelStream()
                .map(task -> {
                    TaskDto taskDto = new TaskDto();
                    taskDto.setTaskId(task.getId());
                    taskDto.setTaskName(task.getName());
                    String leaveRequest = (String) taskService.getVariable(task.getId(), LEAVE_REQUEST_DATA);
                    try {
                        taskDto.setLeaveRequestDto(objectMapper.readValue(leaveRequest, LeaveRequestDto.class));
                    } catch (JsonProcessingException e) {
                        log.error(e);
                    }
                    return taskDto;
                }).collect(Collectors.toList());
    }

    @SneakyThrows
    @Override
    @Transactional
    public void approveOrRejectLeave(LeaveDecisionDto leaveDecisionDto) {
        validateLeaveDecision(leaveDecisionDto);
        Map<String, Object> variables = new HashMap<>();
        variables.put(APPROVED_KEY, leaveDecisionDto.getIsApproved());
        variables.put(LEAVE_DECISION_DATA, objectMapper.writeValueAsString(leaveDecisionDto));
        taskService.complete(leaveDecisionDto.getTaskId(), variables);
    }

    private void validateLeaveRequest(LeaveRequestDto leaveRequestDto, EmployeeEntity employee) {
        if (StringUtils.compare(leaveRequestDto.getDepartment(), employee.getDepartment()) != 0)
            throwBadRequestException(leaveRequestDto.getDepartment() + ERR_DEP_NOT_VALID);
    }

    private void validateLeaveDecision(LeaveDecisionDto leaveDecisionDto) {
        if (taskService.createTaskQuery().taskId(leaveDecisionDto.getTaskId()).list().isEmpty())
            throwNotFoundException(ERR_TASK_NOT_FOUND + leaveDecisionDto.getTaskId());
        if(!employeeService.isExistsById(leaveDecisionDto.getDecisionBy()))
            throwNotFoundException(ERR_EMP_NOT_FOUND + leaveDecisionDto.getDecisionBy());
    }
}
