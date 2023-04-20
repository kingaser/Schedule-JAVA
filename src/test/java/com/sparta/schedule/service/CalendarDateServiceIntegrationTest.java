package com.sparta.schedule.service;

import com.sparta.schedule.dto.request.CalendarDateRequestDto;
import com.sparta.schedule.dto.response.CalendarDateResponseDto;
import com.sparta.schedule.entity.CalendarDate;
import com.sparta.schedule.repository.CalendarDateRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(properties = "spring.config.location=classpath:/application-test.yml")
class CalendarDateServiceIntegrationTest {
    @Autowired CalendarDateService calendarDateService;
    @Autowired CalendarDateRepository calendarDateRepository;

    @BeforeEach
    void initEach(){
        // 테스트 실행 전 사전 작업
        System.out.println("CalendarDateService 테스트 코드 시작(Each)");
    }

    @BeforeAll
    static void initAll(){
        // 테스트 실행 전 사전 작업
        System.out.println("CalendarDateService 테스트 코드 시작(All)");
    }

    @AfterEach
    void destroyEach(){
        // 테스트 실행 후 후처리 작업
        System.out.println("CalendarDateService 테스트 코드 종료(Each)");
    }

    @AfterAll
    static void destroyAll(){
        // 테스트 실행 후 후처리 작업
        System.out.println("CalendarDateService 테스트 코드 종료(All)");
    }

    @Test
    @DisplayName("Date 생성 통합 테스트")
    void createDate() {
        // given
        CalendarDateRequestDto expected = new CalendarDateRequestDto("date");

        // when
        ResponseEntity<CalendarDateResponseDto> actual = calendarDateService.createDate(expected);

        // then
        assertThat(actual).extracting(ResponseEntity::getStatusCode).isEqualTo(HttpStatus.OK);
        assertThat(actual).extracting(ResponseEntity::getBody).extracting(CalendarDateResponseDto::getDate).isEqualTo(expected.getDate());
    }

    @Test
    @DisplayName("Date 조회 통합 테스트")
    void getDate(){
        // given
        CalendarDate expected = CalendarDate.builder().calendarDateRequestDto(new CalendarDateRequestDto("now")).build();
        calendarDateRepository.save(expected);

        // when
        ResponseEntity<CalendarDateResponseDto> actual = calendarDateService.getDate("now");

        // then
        assertThat(actual).extracting(ResponseEntity::getStatusCode).isEqualTo(HttpStatus.OK);
        assertThat(actual).extracting(ResponseEntity::getBody).extracting(CalendarDateResponseDto::getDate).isEqualTo(expected.getDate());
    }
}