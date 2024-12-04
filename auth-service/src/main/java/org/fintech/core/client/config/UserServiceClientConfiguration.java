package org.fintech.core.client.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.Retryer;
import feign.codec.ErrorDecoder;
import lombok.RequiredArgsConstructor;
import org.fintech.core.client.error.MicroserviceExceptionResponse;
import org.fintech.core.exception.ErrorCode;
import org.fintech.core.exception.ServiceException;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import static java.util.concurrent.TimeUnit.SECONDS;

@Component
@RequiredArgsConstructor
public class UserServiceClientConfiguration implements ErrorDecoder {

    private final ObjectMapper objectMapper;

    private final UserServiceClientProperties userServiceClientProperties;

    @Override
    public Exception decode(String s, Response response) {
        HttpStatus status = HttpStatus.valueOf(response.status());
        MicroserviceExceptionResponse reason;
        try{
            reason = this.parseResponseBody(response.body().toString());
        } catch (JsonProcessingException e) {
            reason = new MicroserviceExceptionResponse();
        }

        if (status.equals(HttpStatus.UNAUTHORIZED)){
            return new ServiceException(ErrorCode.AUTH_ERROR, reason.getMessage());
        }
        if(status.equals(HttpStatus.NOT_FOUND)) {
            return new ServiceException(ErrorCode.NOT_FOUND, reason.getMessage());
        }
        if (status.is4xxClientError()) {
            return new ServiceException(ErrorCode.INVALID_ARGUMENT, reason.getMessage());
        }
        if (status.is5xxServerError()) {
            return new ServiceException(ErrorCode.INTERNAL_ERROR, reason.getMessage());
        }
        return new ServiceException(ErrorCode.SERVICE_UNAVAILABLE, reason.getMessage());
    }

    @Bean
    public Retryer retryer() {
        return new Retryer.Default(userServiceClientProperties.getDelay(),
                SECONDS.toMillis(1),
                userServiceClientProperties.getMaxAttempts());
    }

    private MicroserviceExceptionResponse parseResponseBody(String body) throws JsonProcessingException {
        return objectMapper.readValue(body, MicroserviceExceptionResponse.class);
    }

}
