package org.fintech.core.model;

import lombok.Getter;

public enum FileType {

    PROFILE_PHOTO("profile", "image");

    @Getter
    private final String bucketName;

    private final String allowedContentTypePrefix;

    FileType(String bucketName, String typePrefix) {
        this.bucketName = bucketName;
        this.allowedContentTypePrefix = typePrefix;
    }

    public boolean isContentTypeAllowed(String contentType) {
        return contentType != null && contentType.startsWith(allowedContentTypePrefix + "/");
    }
}
