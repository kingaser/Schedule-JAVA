package com.sparta.schedule.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    NOT_VALID_TOKEN(400, "토큰이 유효하지 않습니다."),
    NOT_WRITER(400, "작성자만 삭제/수정할 수 있습니다."),
    DUPLICATED_USERNAME(400, "중복된 Email 입니다."),
    NOT_MATCHING_INFO(400, "회원을 찾을 수 없습니다."),
    NOT_MATCHING_PASSWORD(400, "비밀번호가 일치하지 않습니다."),
    NOT_FOUND_USER(400, "사용자가 존재하지 않습니다."),
    NOT_FOUND_WRITING(400, "스케쥴이 존재하지 않습니다."),

    ;

    private int code;
    private String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}