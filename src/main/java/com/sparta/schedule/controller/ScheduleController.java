package com.sparta.schedule.controller;

import com.sparta.schedule.dto.request.CalendarDateRequestDto;
import com.sparta.schedule.dto.request.CompleteRequestDto;
import com.sparta.schedule.dto.response.MessageResponseDto;
import com.sparta.schedule.dto.request.ScheduleRequestDto;
import com.sparta.schedule.dto.response.ScheduleResponseDto;
import com.sparta.schedule.security.UserDetailsImpl;
import com.sparta.schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/date")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping("/schedule")
    public ResponseEntity<ScheduleResponseDto> createSchedule(@RequestBody ScheduleRequestDto scheduleRequestDto,
                                                              @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return scheduleService.createSchedule(scheduleRequestDto, userDetails);
    }

    @GetMapping("/schedule/{id}")
    public ResponseEntity<ScheduleResponseDto> getSchedule(@PathVariable Long id) {
        return scheduleService.getSchedule(id);
    }

    @PutMapping("/schedule/{id}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(@PathVariable Long id,
                                                              @RequestBody ScheduleRequestDto scheduleRequestDto,
                                                              @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return scheduleService.updateSchedule(id, scheduleRequestDto, userDetails);
    }

    @DeleteMapping("/schedule/{id}")
    public ResponseEntity<MessageResponseDto> deleteSchedule(@PathVariable Long id,
                                                             @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return scheduleService.deleteSchedule(id, userDetails);
    }

    @PatchMapping("/schedule/{id}")
    public String updateCompleteStatus(@PathVariable Long id,
                               @RequestBody CompleteRequestDto requestDto){
        return scheduleService.updateCompleteStatus(id, requestDto);
    }
}

