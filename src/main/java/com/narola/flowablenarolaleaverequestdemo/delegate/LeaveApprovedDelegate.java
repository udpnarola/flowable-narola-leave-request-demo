package com.narola.flowablenarolaleaverequestdemo.delegate;

import com.narola.flowablenarolaleaverequestdemo.component.LeaveDelegateHelper;
import com.narola.flowablenarolaleaverequestdemo.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class LeaveApprovedDelegate implements JavaDelegate {

    private final LeaveDelegateHelper leaveDelegateHelper;
    private final EmailService emailService;

    @Override
    public void execute(DelegateExecution delegateExecution) {
        log.info("Leave approved");
        emailService.sendEmail(leaveDelegateHelper.prepareEmailDto(delegateExecution, true));
    }
}
