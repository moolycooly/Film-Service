package org.fintech.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

@ConfigurationProperties(prefix = "jwt")
@Data
public class JwtProperty {

    private Duration expirationTime;

    private String secret;

}
