package com.sparta.schedule.controller;

import com.sparta.schedule.dto.*;
import com.sparta.schedule.security.UserDetailsImpl;
import com.sparta.schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @GetMapping("/schedule")
    public List<ScheduleResponseDto> getSchedule() {
        return scheduleService.getSchedule();
    }

    @PostMapping("/schedule")
    public ScheduleResponseDto createSchedule(@RequestBody ScheduleRequestDto scheduleRequest,
                                              @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return scheduleService.createSchedule(scheduleRequest, userDetails.getUser());
    }

    @PutMapping("/schedule/{id}")
    public ScheduleResponseDto updateSchedule(@PathVariable Long id,
                                              @RequestBody ScheduleRequestDto scheduleRequest,
                                              @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return scheduleService.updateSchedule(id, scheduleRequest, userDetails.getUser());
    }

    @DeleteMapping("/schedule/{id}")
    public MegResponseDto deleteSchedule(@PathVariable Long id,
                                         @RequestBody ScheduleRequestDto scheduleRequestDto,
                                         @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return scheduleService.deleteSchedule(id, scheduleRequestDto.getDate(), userDetails.getUser());
    }
    @PutMapping("/schedule/complete/{id}")
    public CompleteResponseDto updateStatus(CompleteRequestDto requestDto){
        return scheduleService.updateScheduleStatus(requestDto);
    }
}

