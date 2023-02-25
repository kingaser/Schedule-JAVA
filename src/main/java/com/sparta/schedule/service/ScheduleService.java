package com.sparta.schedule.service;

import com.sparta.schedule.dto.*;
import com.sparta.schedule.entity.Schedule;
import com.sparta.schedule.entity.User;
import com.sparta.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
            ScheduleResponseDto scheduleResponseDto = new ScheduleResponseDto(schedule);
            scheduleResponses.add(scheduleResponseDto);
        }

        return scheduleResponses;
    }

    public ScheduleResponseDto createSchedule(ScheduleRequestDto scheduleRequest, User user) {
        Schedule schedule = scheduleRepository.save(Schedule.builder()
                .scheduleRequest(scheduleRequest)
                .user(user)
                .build());
        return new ScheduleResponseDto(schedule);
    }

    public ScheduleResponseDto updateSchedule(Long id, ScheduleRequestDto scheduleRequest, User user) {

        Optional<Schedule> schedule = scheduleRepository.findByIdAndDateAndUserId(id, scheduleRequest.getDate(), user.getId());
        if (schedule.isEmpty()) {
            throw new IllegalArgumentException("해당 스케쥴이 없습니다.");
        }

        schedule.get().update(scheduleRequest, user);

        return ScheduleResponseDto.builder()
                .schedule(schedule.get())
                .build();
    }


    public MegResponseDto deleteSchedule(Long id, String date, User user) {

        Optional<Schedule> schedule = scheduleRepository.findByIdAndDateAndUserId(id, date, user.getId());
        if (schedule.isEmpty()) {
            throw new IllegalArgumentException("해당 스케쥴이 없습니다.");
        }

        scheduleRepository.deleteByIdAndDateAndUserId(id, date, user.getId());

        return MegResponseDto.User_ServiceCode(HttpStatus.OK, "스케쥴 삭제 완료");
    }

    public String updateScheduleStatus(CompleteRequestDto requestDto) {
        Schedule schedule = scheduleRepository.findById(requestDto.getId()).orElseThrow(
                () -> new IllegalArgumentException("해당하는 일정이 없습니다.")
        );
        schedule.updateStatus(requestDto.isDone());
        return "success";
    }
}
