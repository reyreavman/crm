package ru.rrk.api.dto.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class ServiceErrorDTO {
    private final String message;
    private final LocalDateTime creationDateTime = LocalDateTime.now();
}
