package org.fintech.config.storage;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

import java.net.URI;

@Configuration
@EnableConfigurationProperties(FileStorageProperties.class)
public class FileStorageConfig {

    @Bean
    public S3Client s3Client(FileStorageProperties fileStorageProperties) {
        return S3Client.builder()
                .endpointOverride(URI.create(fileStorageProperties.getEndpoint()))
                .region(Region.of(fileStorageProperties.getRegion()))
                .credentialsProvider(
                        StaticCredentialsProvider.create(AwsBasicCredentials.create(
                                fileStorageProperties.getAccessKey(), fileStorageProperties.getSecretKey()))
                )
                .forcePathStyle(true)
                .build();
    }
}
