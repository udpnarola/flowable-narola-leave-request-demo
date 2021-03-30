package com.narola.flowablenarolaleaverequestdemo.delegate;

import com.narola.flowablenarolaleaverequestdemo.component.LeaveDelegateHelper;
import com.narola.flowablenarolaleaverequestdemo.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class LeaveCancelledDelegate implements JavaDelegate {

    private final LeaveDelegateHelper leaveDelegateHelper;
    private final EmailService emailService;

    @SneakyThrows
    @Override
    public void execute(DelegateExecution delegateExecution) {
        log.info("Leave cancelled");
        emailService.sendEmail(leaveDelegateHelper.prepareEmailDto(delegateExecution, false));

    }

}
