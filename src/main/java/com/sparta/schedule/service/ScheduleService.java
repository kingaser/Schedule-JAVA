package com.sparta.schedule.service;

import com.sparta.schedule.dto.ScheduleRequest;
import com.sparta.schedule.dto.ScheduleResponse;
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
    public List<ScheduleResponse> getSchedule() {
        List<Schedule> schedules = scheduleRepository.findAll();
        List<ScheduleResponse> scheduleResponses = new ArrayList<>();

        for (Schedule schedule : schedules) {
            scheduleResponses.add(ScheduleResponse.from(schedule));
        }

        return scheduleResponses;
    }

    public ScheduleResponse createSchedule(ScheduleRequest scheduleRequest, User user) {

        Schedule schedule = scheduleRepository.save(Schedule.of(scheduleRequest, user));
        return ScheduleResponse.from(schedule);
    }

    public ScheduleResponse updateSchedule(Long id, ScheduleRequest scheduleRequest, User user) {

        Schedule schedule = scheduleRepository.findByIdAndUserId(id, user.getId());

        return null;
    }
}
