package com.sparta.schedule.exception;

import com.sparta.schedule.dto.response.MessageResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class GlobalExceptionHandling {
    public static ResponseEntity<MessageResponseDto> responseException(ErrorCode errorCode) {
        MessageResponseDto messageResponse = MessageResponseDto.builder()
                .code(errorCode.getCode())
                .msg(errorCode.getMessage())
                .build();
        return ResponseEntity.badRequest().body(messageResponse);
    }

    public static ResponseEntity<MessageResponseDto> responseException(HttpStatus status, String message) {
        MessageResponseDto messageResponse = MessageResponseDto.builder()
                .code(status.value())
                .msg(message)
                .build();
        return ResponseEntity.badRequest().body(messageResponse);
    }
}
