package com.sparta.schedule.service;

import com.sparta.schedule.dto.request.CalendarDateRequestDto;
import com.sparta.schedule.dto.request.ScheduleRequestDto;
import com.sparta.schedule.dto.response.ScheduleResponseDto;
import com.sparta.schedule.entity.CalendarDate;
import com.sparta.schedule.entity.Schedule;
import com.sparta.schedule.entity.User;
import com.sparta.schedule.repository.CalendarDateRepository;
import com.sparta.schedule.repository.ScheduleRepository;
import com.sparta.schedule.security.UserDetailsImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = {ScheduleServiceTest.class}, properties = "spring.config.location=classpath:/application-test.yml")
class ScheduleServiceTest {

    @MockBean
    private CalendarDateRepository calendarDateRepository;
    @MockBean
    private ScheduleRepository scheduleRepository;
    @InjectMocks
    private ScheduleService scheduleService;

    @BeforeEach
    void initEach() {
        calendarDateRepository.save(CalendarDate.builder()
                .calendarDateRequestDto(new CalendarDateRequestDto("date"))
                .build());
    }

    @Test
    @DisplayName("스케쥴 전체 조회 단위 테스트")
    void getSchedule() {
        //given

        //when

        //then
    }

    @Test
    @DisplayName("스케쥴 생성 단위 테스트")
    void createSchedule() {
        //given
        User user = User.builder()
                .email("test@test.test")
                .password("test123!")
                .username("test")
                .build();
        UserDetailsImpl userDetails = new UserDetailsImpl(user, "test");
//        CalendarDate calendarDate = calendarDateRepository.save(CalendarDate.builder()
//                .calendarDateRequestDto(new CalendarDateRequestDto("date"))
//                .build());
        ScheduleRequestDto request = new ScheduleRequestDto("제목", "작성자", "내용");
        Schedule expected = Schedule.builder()
                .scheduleRequestDto(request)
                .calendarDate(CalendarDate.builder()
                        .calendarDateRequestDto(new CalendarDateRequestDto("date"))
                        .build())
                .build();
        given(scheduleRepository.save(any(Schedule.class))).willReturn(expected);
        ScheduleResponseDto expectResponse = ScheduleResponseDto.builder()
                .schedule(expected)
                .build();

        //when
        ResponseEntity<ScheduleResponseDto> actual = scheduleService.createSchedule("date", request, userDetails);

        //then
        assertThat(actual).extracting(ResponseEntity::getStatusCode).isEqualTo(HttpStatus.OK);
        assertThat(actual).extracting(ResponseEntity::getBody).isEqualTo(expectResponse);
    }

    @Test
    @DisplayName("스케쥴 상세 조회 단위 테스트")
    void testGetSchedule() {
        //given

        //when

        //then
    }

    @Test
    @DisplayName("스케쥴 수정 단위 테스트")
    void updateSchedule() {
        //given

        //when

        //then
    }

    @Test
    @DisplayName("스케쥴 삭제 단위 테스트")
    void deleteSchedule() {
        //given

        //when

        //then
    }

    @Test
    @DisplayName("스케쥴 완료 단위 테스트")
    void patchComplete() {
        //given

        //when

        //then
    }

    @Test
    @DisplayName("스케쥴 완료 취소 단위 테스트")
    void cancelComplete() {
        //given

        //when

        //then
    }
}