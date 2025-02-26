package com.example.community_feed.image;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final AmazonS3 amazonS3;
    // todo 파일 확장자, 파일 크기 설정
    @Value("${aws.bucket}")
    private String bucket;

    public String upload(MultipartFile multipartFile) {
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
}
