package com.example.community_feed.image;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final AmazonS3 amazonS3;

    @Value("${aws.bucket}")
    private String bucket;

    private static final String[] PERMITTED_FILE_EXTENSIONS = {"jpg", "png"};

    public String upload(MultipartFile multipartFile) {
        if (!isValid(multipartFile)) {
            throw new RuntimeException("올바른 파일이 아닙니다");
        }
        String originalFilename = multipartFile.getOriginalFilename();

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(multipartFile.getContentType());
        objectMetadata.setContentLength(multipartFile.getSize());

        try {
            amazonS3.putObject(bucket, originalFilename, multipartFile.getInputStream(), objectMetadata);
        } catch (IOException e) {
            throw new RuntimeException("파일 업로드 실패", e);
        }
        return amazonS3.getUrl(bucket, originalFilename).toString();

    }

    private boolean isExtension(String name) {
        String extension = name.substring(name.lastIndexOf('.') + 1).toLowerCase();
        return Arrays.asList(PERMITTED_FILE_EXTENSIONS).contains(extension);
    }

    private boolean isValid(MultipartFile file) {
        if (!isExtension(file.getOriginalFilename()) || file.getSize() > 5 * 1024 * 1024) {
            return false;
        }

        return true;
    }
}