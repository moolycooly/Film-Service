package org.fintech.api.error;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.fintech.core.exception.ErrorCode;
import org.springframework.http.HttpStatus;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ErrorCodeProperties {

    public static String getDescription(ErrorCode errorCode) {
        return switch (errorCode) {
            case INTERNAL_ERROR -> "Ошибка сервера";
            case SERVICE_UNAVAILABLE -> "Сервис недоступен";
            case NOT_IMPLEMENTED -> "Запрос не поддерживается данной версией апи";
            case NOT_FOUND -> "Сущность не найдена";
            case AUTHENTICATION_FAILED -> "Неверные данные";
            case INVALID_ARGUMENT -> "Некорректные поля запроса";
        };
    }

    public static HttpStatus getHttpStatus(ErrorCode errorCode) {
        return switch (errorCode) {
            case INTERNAL_ERROR -> HttpStatus.INTERNAL_SERVER_ERROR;
            case SERVICE_UNAVAILABLE -> HttpStatus.SERVICE_UNAVAILABLE;
            case NOT_IMPLEMENTED -> HttpStatus.NOT_IMPLEMENTED;
            case NOT_FOUND -> HttpStatus.NOT_FOUND;
            case AUTHENTICATION_FAILED -> HttpStatus.UNAUTHORIZED;
            default -> HttpStatus.BAD_REQUEST;
        };
    }
}