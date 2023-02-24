package com.sparta.schedule.service;

import com.sparta.schedule.dto.ScheduleRequestDto;
import com.sparta.schedule.dto.ScheduleResponseDto;
import com.sparta.schedule.entity.Schedule;
import com.sparta.schedule.login.entity.User;
import com.sparta.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Transactional(readOnly = true)
    public List<ScheduleResponseDto> getSchedule() {
        List<Schedule> schedules = scheduleRepository.findAll();
        List<ScheduleResponseDto> scheduleResponses = new ArrayList<>();

        for (Schedule schedule : schedules) {
            scheduleResponses.add(ScheduleResponseDto.from(schedule));
        }

        return scheduleResponses;
    }

    public ScheduleResponseDto createSchedule(ScheduleRequestDto scheduleRequest, User user) {

        Schedule schedule = scheduleRepository.save(Schedule.of(scheduleRequest, user));
        return ScheduleResponseDto.from(schedule);
    }

    public ScheduleResponseDto updateSchedule(Long id, ScheduleRequestDto scheduleRequest, User user) {

        Schedule schedule = scheduleRepository.findByIdAndUserId(id, user.getId());

        return null;
    }
}
