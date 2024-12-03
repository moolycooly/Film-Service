package org.fintech.core.client.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "integration.user-service")
@Data
public class UserServiceClientProperties {

    private String name;

    private Integer maxAttempts;

    private Long delay;

}
