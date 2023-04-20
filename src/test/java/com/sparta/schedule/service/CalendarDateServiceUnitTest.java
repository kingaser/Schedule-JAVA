package com.sparta.schedule.service;

import com.sparta.schedule.dto.request.CalendarDateRequestDto;
import com.sparta.schedule.dto.response.CalendarDateResponseDto;
import com.sparta.schedule.entity.CalendarDate;
import com.sparta.schedule.repository.CalendarDateRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

// Spring Boot Slices Test vs SpringExtension (MockitoExtension)
@SpringBootTest(classes = {CalendarDateService.class}, properties = "spring.config.location=classpath:/application-test.yml")
class CalendarDateServiceUnitTest {
    @MockBean CalendarDateRepository calendarDateRepository;
    @Autowired CalendarDateService calendarDateService;

    @Test
    @DisplayName("Date 생성 단위 테스트")
    void createDate() {
        // given
        CalendarDateRequestDto request = new CalendarDateRequestDto("date");
        CalendarDate expected = CalendarDate.builder().calendarDateRequestDto(request).build();
        given(calendarDateRepository.save(any(CalendarDate.class))).willReturn(expected);
        CalendarDateResponseDto expectedResponse = new CalendarDateResponseDto(expected);

        // when
        ResponseEntity<CalendarDateResponseDto> actual = calendarDateService.createDate(request);

        // then
        // Assertions AssertJ
        assertThat(actual).extracting(ResponseEntity::getStatusCode).isEqualTo(HttpStatus.OK);
        assertThat(actual).extracting(ResponseEntity::getBody).usingRecursiveComparison().isEqualTo(expectedResponse);
    }
}