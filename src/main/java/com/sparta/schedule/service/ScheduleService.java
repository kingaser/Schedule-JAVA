package com.sparta.schedule.service;

import com.sparta.schedule.dto.ScheduleRequestDto;
import com.sparta.schedule.dto.ScheduleResponseDto;
import com.sparta.schedule.dto.MegResponseDto;
import com.sparta.schedule.entity.Schedule;
import com.sparta.schedule.entity.UserRoleEnum;
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

    @Transactional(readOnly = true)
    public ResponseEntity<List<ScheduleResponseDto>> getSchedule() {
        List<Schedule> schedules = scheduleRepository.findAll();
        List<ScheduleResponseDto> scheduleResponseDtos = new ArrayList<>();
        for (Schedule schedule : schedules) {
            ScheduleResponseDto scheduleResponseDto = new ScheduleResponseDto(schedule);
            scheduleResponseDtos.add(scheduleResponseDto);
        }
        return ResponseEntity.ok(scheduleResponseDtos);
    }

    @Transactional
    public ResponseEntity<ScheduleResponseDto> createSchedule(ScheduleRequestDto scheduleRequestDto, UserDetailsImpl userDetails) {

        Schedule schedule = scheduleRepository.save(Schedule.builder()
                .scheduleRequestDto(scheduleRequestDto)
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

        // 해당 게시글이 있다면 게시글 객체를 Dto 로 변환  후 ResponseEntity body 에 담아서 리턴한다
        return ResponseEntity.ok(new ScheduleResponseDto(schedule.get()));
    }

    @Transactional
    public ResponseEntity<ScheduleResponseDto> updateSchedule(Long id, ScheduleRequestDto scheduleRequestDto, UserDetailsImpl userDetails) {

        Optional<Schedule> found = scheduleRepository.findByUser(userDetails.getUser());
        if (found.isEmpty() && userDetails.getUser().getRole() == UserRoleEnum.USER) {
            throw new IllegalArgumentException("작성자가 일치하지 않습니다.");
        }

        Optional<Schedule> schedule = scheduleRepository.findByIdAndDate(id, scheduleRequestDto.getDate());
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
    public ResponseEntity<MegResponseDto> deleteSchedule(Long id, ScheduleRequestDto scheduleRequestDto, UserDetailsImpl userDetails) {

        Optional<Schedule> found = scheduleRepository.findByUser(userDetails.getUser());
        if (found.isEmpty() && userDetails.getUser().getRole() == UserRoleEnum.USER) {
            throw new IllegalArgumentException("작성자가 일치하지 않습니다.");
        }

        Optional<Schedule> schedule = scheduleRepository.findByIdAndDate(id, scheduleRequestDto.getDate());
        if (schedule.isEmpty()) {
            throw new IllegalArgumentException("스케쥴이 존재하지 않습니다.");
        }

        scheduleRepository.deleteById(id);
        return ResponseEntity.ok()
                .body(MegResponseDto.User_ServiceCode(HttpStatus.OK, "삭제 완료"));

    }
}
