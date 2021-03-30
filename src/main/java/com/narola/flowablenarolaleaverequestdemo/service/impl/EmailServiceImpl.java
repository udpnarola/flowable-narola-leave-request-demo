package com.narola.flowablenarolaleaverequestdemo.service.impl;

import com.narola.flowablenarolaleaverequestdemo.dto.EmailDto;
import com.narola.flowablenarolaleaverequestdemo.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private static final String LEAVE_APPROVED_SUBJECT = "Leave Approved";
    private static final String LEAVE_CANCEL_SUBJECT = "Leave Canceled";
    private static final String MESSAGE_TEMPLATE = "Dear %s, Your leave is %s by %s.";

    private final JavaMailSender emailSender;

    @Override
    public void sendEmail(EmailDto emailDto) {
        log.info("Ready to send an email {}", emailDto);
        emailSender.send(prepareMessage(emailDto));
        log.info("Email successfully sent.");
    }

    private SimpleMailMessage prepareMessage(EmailDto emailDto) {
        String approvedOrCanceled;
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(emailDto.getRequestedBy().getEmail());
        if (Boolean.TRUE.equals(emailDto.getIsApproved())) {
            simpleMailMessage.setSubject(LEAVE_APPROVED_SUBJECT);
            approvedOrCanceled = "approved";
        } else {
            simpleMailMessage.setSubject(LEAVE_CANCEL_SUBJECT);
            approvedOrCanceled = "canceled";
        }
        String message = String.format(MESSAGE_TEMPLATE, emailDto.getRequestedBy().getFirstName(),
                approvedOrCanceled, emailDto.getDecisionBy().getFirstName());
        simpleMailMessage.setText(message);
        return simpleMailMessage;
    }
}
