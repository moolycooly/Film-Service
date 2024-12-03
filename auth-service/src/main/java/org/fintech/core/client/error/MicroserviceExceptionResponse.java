package org.fintech.core.client.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MicroserviceExceptionResponse {
    private Integer code;
    private String message;
}
