package com.narola.flowablenarolaleaverequestdemo.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constants {

    //Flowable
    public static final String LEAVE_REQUEST_PROCESS_ID = "narolaLeaveRequest";
    public static final String TL_APPROVAL_TASK_ASSIGNEE = "dep";
    public static final String LEAVE_REQUEST_DATA = "leaveRequestData";
    public static final String LEAVE_DECISION_DATA = "leaveDecisionData";
    public static final String APPROVED_KEY = "approved";

    //Error message
    public static final String ERR_EMP_ID_AVAILABLE = "New employee should not have an id";
    public static final String ERR_EMP_ID_NOT_AVAILABLE = "Enter id to update employee";

    public static final String ERR_EMP_NOT_FOUND = "Employee not found for the id: ";
    public static final String ERR_EMP_NOT_FOUND_BY_EMAIL = "Employee not found for the email: ";

    public static final String ERR_TASK_NOT_FOUND = "Task not found for the id: ";

    public static final String ERR_EMP_EMAIL_AVAILABLE = "Email already exists please enter other email";

    public static final String ERR_DEP_NOT_VALID = " department is not valid";

}
