package com.narola.flowablenarolaleaverequestdemo.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.narola.flowablenarolaleaverequestdemo.dto.EmailDto;
import com.narola.flowablenarolaleaverequestdemo.dto.LeaveDecisionDto;
import com.narola.flowablenarolaleaverequestdemo.dto.LeaveRequestDto;
import com.narola.flowablenarolaleaverequestdemo.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.flowable.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static com.narola.flowablenarolaleaverequestdemo.constant.Constants.LEAVE_DECISION_DATA;
import static com.narola.flowablenarolaleaverequestdemo.constant.Constants.LEAVE_REQUEST_DATA;

@Component
@RequiredArgsConstructor
public class LeaveDelegateHelper {

    private final ObjectMapper objectMapper;
    private final EmployeeService employeeService;

    @SneakyThrows
    @Transactional
    public EmailDto prepareEmailDto(DelegateExecution delegateExecution, Boolean isLeaveApproved) {
        EmailDto emailDto = new EmailDto();
        LeaveRequestDto leaveRequestDto = objectMapper
                .readValue((String) delegateExecution.getVariable(LEAVE_REQUEST_DATA), LeaveRequestDto.class);
        LeaveDecisionDto leaveDecisionDto = objectMapper
                .readValue((String) delegateExecution.getVariable(LEAVE_DECISION_DATA), LeaveDecisionDto.class);
        emailDto.setRequestedBy(employeeService.getById(leaveRequestDto.getEmpId()));
        emailDto.setDecisionBy(employeeService.getById(leaveDecisionDto.getDecisionBy()));
        emailDto.setIsApproved(isLeaveApproved);
        return emailDto;
    }
}
