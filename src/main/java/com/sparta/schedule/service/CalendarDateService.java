package com.sparta.schedule.service;

import com.sparta.schedule.dto.request.CalendarDateRequestDto;
import com.sparta.schedule.dto.response.CalendarDateResponseDto;
import com.sparta.schedule.entity.CalendarDate;
import com.sparta.schedule.repository.CalendarDateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional
public class CalendarDateService {

    private final CalendarDateRepository calendarDateRepository;

    public ResponseEntity<CalendarDateResponseDto> createDate(CalendarDateRequestDto calendarDateRequestDto) {
        CalendarDate calendarDate = calendarDateRepository.save(CalendarDate.builder()
                .calendarDateRequestDto(calendarDateRequestDto)
                .build());
        return ResponseEntity.ok(new CalendarDateResponseDto(calendarDate));
    }

}
