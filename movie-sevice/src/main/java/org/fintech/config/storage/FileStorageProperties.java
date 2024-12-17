package org.fintech.config.storage;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "integration.s3")
public class FileStorageProperties {

    private String region;

    private String accessKey;

    private String secretKey;

    private String endpoint;

}
