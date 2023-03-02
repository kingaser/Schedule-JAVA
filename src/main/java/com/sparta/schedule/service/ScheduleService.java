package com.sparta.schedule.service;

import com.sparta.schedule.dto.request.CompleteRequestDto;
import com.sparta.schedule.dto.request.ScheduleRequestDto;
import com.sparta.schedule.dto.response.CompleteResponseDto;
import com.sparta.schedule.dto.response.MessageResponseDto;
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
    public ResponseEntity<ScheduleResponseDto> createSchedule(String date,
                                                              ScheduleRequestDto scheduleRequestDto,
                                                              UserDetailsImpl userDetails) {
        CalendarDate calendarDate = calendarDateRepository.findByDate(date);
        Schedule schedule = scheduleRepository.save(Schedule.builder()
                .scheduleRequestDto(scheduleRequestDto)
                .calendarDate(calendarDate)
                .user(userDetails.getUser())
                .build());
//          데이터 null값 예외처리
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
    public ResponseEntity<ScheduleResponseDto> updateSchedule(String date,
                                                              Long id,
                                                              ScheduleRequestDto scheduleRequestDto,
                                                              UserDetailsImpl userDetails) {

        foundUser(id, userDetails);

        CalendarDate calendarDate = calendarDateRepository.findByDate(date);

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
    public ResponseEntity<MessageResponseDto> deleteSchedule(String date,
                                                             Long id,
                                                             UserDetailsImpl userDetails) {

        foundUser(id, userDetails);

        CalendarDate calendarDate = calendarDateRepository.findByDate(date);

        Optional<Schedule> schedule = scheduleRepository.findById(id);
        if (schedule.isEmpty()) {
            throw new IllegalArgumentException("스케쥴이 존재하지 않습니다.");
        }

        scheduleRepository.deleteById(id);
        return ResponseEntity.ok()
                .body(MessageResponseDto.User_ServiceCode(HttpStatus.OK, "삭제 완료"));
    }

    @Transactional
    public ResponseEntity<CompleteResponseDto> patchComplete(Long id,
                                                             CompleteRequestDto completeRequestDto,
                                                             UserDetailsImpl userDetails) {
        scheduleRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 일정이 없습니다.")
        );

        Schedule check = scheduleRepository.findByIdAndComplete(id, !completeRequestDto.isComplete()).orElseThrow(
                () -> new IllegalArgumentException("이미 완료된 스케쥴입니다.")
        );

        check.updateCompleteStatus(completeRequestDto);
        return ResponseEntity.ok().body(
                CompleteResponseDto.User_ServiceCode(HttpStatus.OK, true, "스케쥴 완료")
        );
    }

    @Transactional
    public ResponseEntity<CompleteResponseDto> cancelComplete(Long id,
                                                             CompleteRequestDto completeRequestDto,
                                                             UserDetailsImpl userDetails) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 일정이 없습니다.")
        );

        Schedule check = scheduleRepository.findByIdAndComplete(id, !completeRequestDto.isComplete()).orElseThrow(
                () -> new IllegalArgumentException("완료가 취소된 스케쥴입니다.")
        );

        schedule.updateCompleteStatus(completeRequestDto);
        return ResponseEntity.ok().body(
                CompleteResponseDto.User_ServiceCode(HttpStatus.OK, false, "완료 취소")
        );
    }

    private void foundUser(Long id, UserDetailsImpl userDetails) {
        Optional<Schedule> found = scheduleRepository.findByIdAndUser(id, userDetails.getUser());
        if (found.isEmpty() && userDetails.getUser().getRole() == UserRoleEnum.USER) {
            throw new IllegalArgumentException("작성자가 일치하지 않습니다.");
        }
    }
}
