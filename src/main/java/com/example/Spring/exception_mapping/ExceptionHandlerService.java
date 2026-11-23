package com.example.Spring.exception_mapping;

import com.example.Spring.exception.AppException;
import com.example.Spring.exception_dto.ExceptionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionHandlerService {

    @ExceptionHandler(AppException.class)
    private ResponseEntity<ExceptionDTO> handler(AppException ex) {
        ExceptionDTO exceptionDTO = ExceptionDTO.builder().message(ex.getMessage())
            .status(ex.getStatus()).dateTime(LocalDateTime.now()).build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionDTO);
    }
}
