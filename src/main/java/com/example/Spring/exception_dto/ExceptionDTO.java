package com.example.Spring.exception_dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Builder
public record ExceptionDTO(
    @JsonProperty("message")
    String message,
    @JsonProperty("status")
    HttpStatus status,
    @JsonProperty("date")
    LocalDateTime dateTime
)
{
}
