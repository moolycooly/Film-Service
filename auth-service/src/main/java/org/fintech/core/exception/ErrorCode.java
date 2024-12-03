package org.fintech.core.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    INTERNAL_ERROR(200),

    SERVICE_UNAVAILABLE(201),

    AUTH_ERROR(202),

    INVALID_TOKEN(203),

    INVALID_ARGUMENT(204);

    private final Integer code;
}
