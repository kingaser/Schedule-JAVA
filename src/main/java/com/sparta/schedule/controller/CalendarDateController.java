package com.sparta.schedule.controller;

import com.sparta.schedule.dto.request.CalendarDateRequestDto;
import com.sparta.schedule.dto.request.ScheduleRequestDto;
import com.sparta.schedule.dto.response.CalendarDateResponseDto;
import com.sparta.schedule.dto.response.ScheduleResponseDto;
import com.sparta.schedule.service.CalendarDateService;
import com.sparta.schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class CalendarDateController {

    private final CalendarDateService calendarDateService;
    private final ScheduleService scheduleService;

    @PostMapping("/date")
    public ResponseEntity<CalendarDateResponseDto> createDate(@RequestBody CalendarDateRequestDto calendarDateRequestDto) {
        return calendarDateService.createDate(calendarDateRequestDto);
    }

//    @GetMapping("/date")
//    public List<ResponseEntity<CalendarDateResponseDto>> getDate() {
//        return calendarDateService.getDate();
//    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<ScheduleResponseDto>> getSchedules(@PathVariable String date) {
        return scheduleService.getSchedule(date);
    }

}
