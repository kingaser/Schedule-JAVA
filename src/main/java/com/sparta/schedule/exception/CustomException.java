package com.sparta.schedule.exception;

import com.sparta.schedule.dto.response.MessageResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class CustomException {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MessageResponseDto> methodValidException(MethodArgumentNotValidException e) {
        MessageResponseDto messageResponse = ErrorResponse(e.getMessage());
        return ResponseEntity.badRequest().body(messageResponse);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<MessageResponseDto> commonException(RuntimeException e) {
        MessageResponseDto messageResponse = ErrorResponse(e.getMessage());
        return ResponseEntity.badRequest().body(messageResponse);
    }

    private MessageResponseDto ErrorResponse(BindingResult bindingResult) {
        String message = "";

        if (bindingResult.hasErrors()) {
            message = bindingResult.getAllErrors().get(0).getDefaultMessage();
        }

        return MessageResponseDto.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .msg(message)
                .build();
    }

    private MessageResponseDto ErrorResponse(String message) {
        return MessageResponseDto.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .msg(message)
                .build();
    }

}
