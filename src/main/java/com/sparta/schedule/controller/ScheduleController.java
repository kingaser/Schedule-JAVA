package com.sparta.schedule.controller;

import com.sparta.schedule.dto.ScheduleRequest;
import com.sparta.schedule.dto.ScheduleResponse;
import com.sparta.schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/schedule")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @GetMapping("/")
    public ScheduleResponse getSchedule() {
        return scheduleService.getSchedule();
    }

    @PostMapping("/create")
    public ScheduleResponse createSchedule(@RequestBody ScheduleRequest scheduleRequest) {
        return scheduleService.createSchedule(scheduleRequest);
    }
}
