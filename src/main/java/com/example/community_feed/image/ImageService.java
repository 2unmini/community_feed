package com.example.community_feed.image;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final AmazonS3Client amazonS3Client;
    // todo 파일 확장자, 파일 크기 설정
    @Value("${aws.bucket}")
    private String bucket;

    public String upload(MultipartFile multipartFile) throws IOException {
        String originalFilename = multipartFile.getOriginalFilename();

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(multipartFile.getContentType());
        objectMetadata.setContentLength(multipartFile.getSize());

        amazonS3Client.putObject(bucket, originalFilename, multipartFile.getInputStream(), objectMetadata);
        return amazonS3Client.getUrl(bucket, originalFilename).toString();

    }
}
