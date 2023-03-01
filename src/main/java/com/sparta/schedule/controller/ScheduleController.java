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

    @PostMapping("/{date}")
    public ResponseEntity<ScheduleResponseDto> createSchedule(@PathVariable String date,
                                                              @RequestBody ScheduleRequestDto scheduleRequestDto,
                                                              @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return scheduleService.createSchedule(date, scheduleRequestDto, userDetails);
    }

    @PutMapping("/{date}/{id}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(@PathVariable String date,
                                                              @PathVariable Long id,
                                                              @RequestBody ScheduleRequestDto scheduleRequestDto,
                                                              @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return scheduleService.updateSchedule(date, id, scheduleRequestDto, userDetails);
    }

    @DeleteMapping("/{date}/{id}")
    public ResponseEntity<MessageResponseDto> deleteSchedule(@PathVariable String date,
                                                             @PathVariable Long id,
                                                             @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return scheduleService.deleteSchedule(date, id, userDetails);
    }

    @PatchMapping("/schedule/{id}")
    public String updateCompleteStatus(@PathVariable Long id,
                               @RequestBody CompleteRequestDto requestDto){
        return scheduleService.updateCompleteStatus(id, requestDto);
    }
}