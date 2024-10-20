package ru.rrk.api.dto.error;


import ru.rrk.core.exception.ViolationsException;

import java.time.LocalDateTime;
import java.util.List;

public record ValidationErrorDTO(
        List<ViolationsException> violationsExceptions,
        LocalDateTime creationDateTime
) {
    public ValidationErrorDTO(List<ViolationsException> violationsExceptions) {
        this(violationsExceptions, LocalDateTime.now());
    }

}
