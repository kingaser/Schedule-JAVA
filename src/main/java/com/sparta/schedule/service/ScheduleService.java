package com.sparta.schedule.service;

import com.sparta.schedule.dto.request.CalendarDateRequestDto;
import com.sparta.schedule.dto.request.CompleteRequestDto;
import com.sparta.schedule.dto.response.CalendarDateResponseDto;
import com.sparta.schedule.dto.response.MessageResponseDto;
import com.sparta.schedule.dto.request.ScheduleRequestDto;
import com.sparta.schedule.dto.response.ScheduleResponseDto;
import com.sparta.schedule.entity.CalendarDate;
import com.sparta.schedule.entity.Schedule;
import com.sparta.schedule.entity.UserRoleEnum;
import com.sparta.schedule.repository.CalendarDateRepository;
import com.sparta.schedule.repository.ScheduleRepository;
import com.sparta.schedule.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final CalendarDateRepository calendarDateRepository;

    @Transactional(readOnly = true)
    public ResponseEntity<List<ScheduleResponseDto>> getSchedule(String date) {
        CalendarDate calendarDate = calendarDateRepository.findByDate(date);
        List<Schedule> schedules = scheduleRepository.findAllByCalendarDate(calendarDate);
        List<ScheduleResponseDto> scheduleResponseDtoList = new ArrayList<>();
        for (Schedule schedule : schedules) {
            scheduleResponseDtoList.add(ScheduleResponseDto.builder()
                    .schedule(schedule)
                    .build());
        }
        return ResponseEntity.ok(scheduleResponseDtoList);
    }

    @Transactional
    public ResponseEntity<ScheduleResponseDto> createSchedule(ScheduleRequestDto scheduleRequestDto,
                                                              UserDetailsImpl userDetails) {
        CalendarDate calendarDate = calendarDateRepository.findByDate(scheduleRequestDto.getDate());
        Schedule schedule = scheduleRepository.save(Schedule.builder()
                .scheduleRequestDto(scheduleRequestDto)
                .calendarDate(calendarDate)
                .user(userDetails.getUser())
                .build());

        return ResponseEntity.ok(new ScheduleResponseDto(schedule));
    }

    @Transactional(readOnly = true)
    public ResponseEntity<ScheduleResponseDto> getSchedule(Long id) {
        Optional<Schedule> schedule = scheduleRepository.findById(id);
        if (schedule.isEmpty()) {
            throw new IllegalArgumentException("스케쥴이 존재하지 않습니다.");
        }

        return ResponseEntity.ok(new ScheduleResponseDto(schedule.get()));
    }

    @Transactional
    public ResponseEntity<ScheduleResponseDto> updateSchedule(Long id, ScheduleRequestDto scheduleRequestDto, UserDetailsImpl userDetails) {

        foundUser(id, userDetails);

        Optional<Schedule> schedule = scheduleRepository.findById(id);
        if (schedule.isEmpty()) {
            throw new IllegalArgumentException("스케쥴이 존재하지 않습니다.");
        }

        schedule.get().update(scheduleRequestDto, userDetails.getUser());
        scheduleRepository.saveAndFlush(schedule.get()); // modifiedAt 업데이트를 위해 saveAndFlush 사용
        return ResponseEntity.ok(ScheduleResponseDto.builder()
                .schedule(schedule.get())
                .build());
    }

    @Transactional
    public ResponseEntity<MessageResponseDto> deleteSchedule(Long id, UserDetailsImpl userDetails) {

        foundUser(id, userDetails);


        Optional<Schedule> schedule = scheduleRepository.findById(id);
        if (schedule.isEmpty()) {
            throw new IllegalArgumentException("스케쥴이 존재하지 않습니다.");
        }

        scheduleRepository.deleteById(id);
        return ResponseEntity.ok()
                .body(MessageResponseDto.User_ServiceCode(HttpStatus.OK, "삭제 완료"));
    }

    @Transactional
    public String updateCompleteStatus(Long id, CompleteRequestDto requestDto) {
            Schedule schedule = scheduleRepository.findById(id).orElseThrow(
                    () -> new IllegalArgumentException("해당하는 일정이 존재하지 않습니다.")
            );
            schedule.updateCompleteStatus(requestDto);
            return "success";
    }

    private void foundUser(Long id, UserDetailsImpl userDetails) {
        Optional<Schedule> found = scheduleRepository.findByIdAndUser(id, userDetails.getUser());
        if (found.isEmpty() && userDetails.getUser().getRole() == UserRoleEnum.USER) {
            throw new IllegalArgumentException("작성자가 일치하지 않습니다.");
        }
    }
}
