package com.sparta.schedule.service;

import com.sparta.schedule.dto.ScheduleRequest;
import com.sparta.schedule.dto.ScheduleResponse;
import com.sparta.schedule.entity.Schedule;
import com.sparta.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleResponse createSchedule(ScheduleRequest scheduleRequest) {

        Schedule schedule = scheduleRepository.save()
        return null;
    }

    @Transactional(readOnly = true)
    public ScheduleResponse getSchedule() {

    }
}
