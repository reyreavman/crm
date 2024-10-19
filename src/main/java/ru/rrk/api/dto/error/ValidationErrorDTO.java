package ru.rrk.api.dto.error;


import ru.rrk.core.exception.violation.Violation;

import java.time.LocalDateTime;
import java.util.List;

public record ValidationErrorDTO(
        List<Violation> violations,
        LocalDateTime creationDateTime
) {
    public ValidationErrorDTO(List<Violation> violations) {
        this(violations, LocalDateTime.now());
    }

    public ValidationErrorDTO(List<Violation> violations, LocalDateTime creationDateTime) {
        this.violations = violations;
        this.creationDateTime = creationDateTime;
    }
}
