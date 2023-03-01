package com.sparta.schedule.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@RequiredArgsConstructor
public class ScheduleRequestDto {

    @NotBlank(message = "제목이 없습니다.")
    private String title;
    @NotBlank(message = "작성자가 없습니다.")
    private String author;
    @NotBlank(message = "내용이 없습니다.")
    private String contents;

}

