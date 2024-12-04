package org.fintech.api.error;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.fintech.core.exception.ErrorCode;
import org.springframework.http.HttpStatus;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ErrorCodeProperties {

    public static String getDescription(ErrorCode errorCode) {
        return switch (errorCode){
            case INTERNAL_ERROR -> "Ошибка сервера";
            case SERVICE_UNAVAILABLE -> "Сервис недоступен";
            case INVALID_TOKEN -> "Неверный токен";
            case INVALID_ARGUMENT -> "Некорректные поля запроса";
            case AUTH_ERROR -> "Ошибка авторизации";
            case NOT_FOUND -> "Сущность не найдена";
        };
    }

    public static HttpStatus getHttpStatus(ErrorCode errorCode) {
        return switch (errorCode){
            case INTERNAL_ERROR -> HttpStatus.INTERNAL_SERVER_ERROR;
            case SERVICE_UNAVAILABLE -> HttpStatus.SERVICE_UNAVAILABLE;
            case INVALID_TOKEN -> HttpStatus.UNAUTHORIZED;
            default -> HttpStatus.BAD_REQUEST;
        };
    }
}