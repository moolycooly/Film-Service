package org.fintech.core.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    INTERNAL_ERROR(300),

    SERVICE_UNAVAILABLE(301),

    NOT_FOUND(302),

    INVALID_ARGUMENT(303),

    EMAIL_ALREADY_USED(304),

    NOT_ACCEPTABLE(305),

    NOT_IMPLEMENTED(306),

    AUTHENTICATION_FAILED(307);

    private final Integer code;
}
