package org.fintech.api.error;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fintech.core.exception.ErrorCode;
import org.fintech.core.exception.ServiceException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.*;


@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class ApiExceptionHandler {

    private final MessageSource messageSource;

    @ExceptionHandler(value = ServiceException.class)
    public ResponseEntity<ErrorMessage> handleServiceException(ServiceException ex) {
        return new ResponseEntity<>(
                buildErrorMessage(ex.getErrorCode()),
                ErrorCodeProperties.getHttpStatus(ex.getErrorCode())
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> methodArgumentNotValid(MethodArgumentNotValidException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST,"Invalid request");

        Map<String, String> errors = new HashMap<>();
        e.getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);

        });
        problemDetail.setProperty("errors",errors);

        return new ResponseEntity<>(
                new ErrorMessage(errors.toString(), ErrorCode.INVALID_ARGUMENT.getCode()),
                HttpStatus.BAD_REQUEST
        );
    }

    private ErrorMessage buildErrorMessage(ErrorCode errorCode) {
        return new ErrorMessage(getMessage(errorCode), errorCode.getCode());
    }

    private String getMessage(ErrorCode errorCode) {
        return messageSource.getMessage(
                errorCode.name(),
                null,
                "Неизвестная ошибка, сообщите в поддержку",
                Locale.getDefault()
        );
    }

}
