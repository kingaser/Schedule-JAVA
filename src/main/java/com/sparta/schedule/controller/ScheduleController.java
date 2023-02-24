package com.sparta.schedule.controller;

import com.sparta.schedule.dto.ScheduleRequest;
import com.sparta.schedule.dto.ScheduleResponse;
import com.sparta.schedule.login.security.UserDetailsImpl;
import com.sparta.schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedule")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @GetMapping("/")
    public List<ScheduleResponse> getSchedule() {
        return scheduleService.getSchedule();
    }

    @PostMapping("/create")
    public ScheduleResponse createSchedule(@RequestBody ScheduleRequest scheduleRequest, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return scheduleService.createSchedule(scheduleRequest, userDetails.getUser());
    }

    @PutMapping("/update")
    public ScheduleResponse updateSchedule(@RequestBody ScheduleRequest scheduleRequest, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return scheduleService.updateSchedule(scheduleRequest, userDetails.getUser());
    }
}
