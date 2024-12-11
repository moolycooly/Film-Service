package org.fintech.core.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    INTERNAL_ERROR(100),

    SERVICE_UNAVAILABLE(101),

    NOT_FOUND(102),

    INVALID_ARGUMENT(103),

    NOT_IMPLEMENTED(104),

    AUTHENTICATION_FAILED(105);

    private final Integer code;
}
