package ru.rrk.core.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.rrk.api.dto.error.ServiceErrorDTO;
import ru.rrk.api.dto.error.ValidationErrorDTO;
import ru.rrk.core.exception.service.ServiceException;
import ru.rrk.core.exception.violation.Violation;

import java.util.List;

@RestControllerAdvice
public class ExceptionsHandler {
    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ServiceErrorDTO handleServiceException(ServiceException e) {
        return new ServiceErrorDTO(e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ValidationErrorDTO handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<Violation> violations = e.getBindingResult().getFieldErrors()
                .stream()
                .map(
                        error -> new Violation(error.getField(), error.getDefaultMessage())
                )
                .toList();
        return new ValidationErrorDTO(violations);
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ValidationErrorDTO handleConstraintViolationException(ConstraintViolationException e) {
        List<Violation> violations = e.getConstraintViolations()
                .stream()
                .map(
                        error -> new Violation(
                                error.getInvalidValue().toString(), error.getMessage())
                )
                .toList();
        return new ValidationErrorDTO(violations);
    }
}
