package ru.rrk.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.rrk.api.dto.error.ServiceErrorDTO;
import ru.rrk.core.exception.service.ServiceException;

@RestControllerAdvice
public class ExceptionsHandler {
    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ServiceErrorDTO handleServiceException(ServiceException e) {
        return new ServiceErrorDTO(e.getMessage());
    }
}
