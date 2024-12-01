package org.fintech.api.error;

import org.springframework.http.HttpStatus;

public record ErrorMessage(String message, HttpStatus status) {}
