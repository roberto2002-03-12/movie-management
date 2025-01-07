package com.robert.projects.MovieManagement.util;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import software.amazon.awssdk.services.s3.S3Client;

@Service
public class S3Services {
  private final S3Client s3Client;

  public S3Services(S3Client s3Client) {
    this.s3Client = s3Client;
  }

  public String uploadFile(MultipartFile file) throws IOException {
    String key = generateFileName(file);

    
  }

  private String generateFileName(MultipartFile file) {
    return System.currentTimeMillis() + "_" + file.getOriginalFilename();
  }
}
