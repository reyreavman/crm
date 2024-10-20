package ru.rrk.core.exception.handler;

import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.rrk.api.dto.error.FiltersErrorDTO;
import ru.rrk.api.dto.error.ServiceErrorDTO;
import ru.rrk.api.dto.error.ValidationErrorDTO;
import ru.rrk.core.exception.FiltersException;
import ru.rrk.core.exception.ServiceException;
import ru.rrk.core.exception.ViolationsException;

import java.time.DateTimeException;
import java.time.format.DateTimeParseException;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ServiceErrorDTO handleServiceException(ServiceException e) {
        return new ServiceErrorDTO(e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ValidationErrorDTO handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<ViolationsException> violationsExceptions = e.getBindingResult().getFieldErrors()
                .stream()
                .map(
                        error -> new ViolationsException(error.getField(), error.getDefaultMessage())
                )
                .toList();
        return new ValidationErrorDTO(violationsExceptions);
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ValidationErrorDTO handleConstraintViolationException(ConstraintViolationException e) {
        List<ViolationsException> violationsExceptions = e.getConstraintViolations()
                .stream()
                .map(
                        error -> new ViolationsException(
                                error.getInvalidValue().toString(), error.getMessage())
                )
                .toList();
        return new ValidationErrorDTO(violationsExceptions);
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public FiltersErrorDTO handleFiltersException(FiltersException e) {
        return new FiltersErrorDTO(e.getMessage(), e.getFilters());
    }
}
