package com.narola.flowablenarolaleaverequestdemo.controller;

import com.narola.flowablenarolaleaverequestdemo.dto.LeaveDecisionDto;
import com.narola.flowablenarolaleaverequestdemo.dto.LeaveRequestDto;
import com.narola.flowablenarolaleaverequestdemo.dto.TaskDto;
import com.narola.flowablenarolaleaverequestdemo.service.LeaveRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/leaves")
@RequiredArgsConstructor
public class LeaveRequestController {

    private final LeaveRequestService leaveRequestService;

    @PostMapping
    public ResponseEntity<String> applyLeave(@Valid @RequestBody LeaveRequestDto leaveRequestDto) {
        leaveRequestService.applyLeave(leaveRequestDto);
        return ResponseEntity.ok("Leave successfully applied");
    }

    @GetMapping("/departments/{dep}")
    public List<TaskDto> getDepartmentsTasks(@PathVariable String dep) {
        return leaveRequestService.getDepartmentTasks(dep);
    }

    @PutMapping("/decision")
    public ResponseEntity<String> approveOrRejectLeave(@RequestBody LeaveDecisionDto leaveDecisionDto) {
        leaveRequestService.approveOrRejectLeave(leaveDecisionDto);
        return ResponseEntity.ok("Task finished successfully");
    }

}
