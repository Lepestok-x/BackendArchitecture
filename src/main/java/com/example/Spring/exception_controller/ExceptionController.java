package com.example.Spring.exception_controller;

import com.example.Spring.exception.AppException;
import com.example.Spring.exception_dto.ExceptionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(AppException.class)
    public ResponseEntity<ExceptionDTO> handlerNotFound(AppException ex) {
        HttpStatus status = (ex.getStatus() != null) ? ex.getStatus() : HttpStatus.INTERNAL_SERVER_ERROR;
        ExceptionDTO exceptionDTO =
            ExceptionDTO.builder()
            .message(ex.getMessage())
            .status(status)
            .dateTime(LocalDateTime.now())
            .build();
        return ResponseEntity.status(status).body(exceptionDTO);
    }
}
