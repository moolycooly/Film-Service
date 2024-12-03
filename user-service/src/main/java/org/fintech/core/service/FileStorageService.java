package org.fintech.core.service;

import lombok.RequiredArgsConstructor;
import org.fintech.config.storage.FileStorageProperties;
import org.fintech.core.model.FileType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileStorageService {

    private final S3Client s3Client;

    private final FileStorageProperties fileStorageProperties;

    public String uploadFile(MultipartFile file, FileType bucket) throws IOException {
        String contentType = file.getContentType();
        if (!bucket.isContentTypeAllowed(contentType)) {
            throw new RuntimeException("Тип файла не разрешен для корзины: " + bucket.getBucketName());
        }
        String bucketName = bucket.getBucketName();
        String uniqueId = UUID.randomUUID().toString();
        String mediaType = contentType.split("/")[1];
        String fileKeyName = String.format("%s.%s", uniqueId, mediaType);
        String avatarId = String.format("%s.%s.%s", bucketName, uniqueId, mediaType);
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(fileKeyName)
                .contentType(contentType)
                .build();
        s3Client.putObject(putObjectRequest, RequestBody.fromInputStream(file.getInputStream(), file.getSize()));
        return avatarId;
    }

    public byte[] downloadFile(String bucketName, String fileKeyName) throws IOException {
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(bucketName)
                .key(fileKeyName)
                .build();
        return s3Client.getObject(getObjectRequest).readAllBytes();
    }

    public void deleteFile(String fileKey) {
        String[] parsedKey = fileKey.split("\\.");
        if (parsedKey.length != 3) {
            throw new RuntimeException("Тип файла не разрешен для корзины: " + fileKey);
        }

        String bucketName = parsedKey[0];
        String uniqueId = parsedKey[1];
        String mediaType = parsedKey[2];
        String fileKeyName = String.format("%s.%s", uniqueId, mediaType);

        DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                .bucket(bucketName)
                .key(fileKeyName)
                .build();
        s3Client.deleteObject(deleteObjectRequest);
    }

}

