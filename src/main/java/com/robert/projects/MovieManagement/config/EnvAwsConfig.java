package com.robert.projects.MovieManagement.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EnvAwsConfig {

  @Value("${AWS_BUCKET_NAME}")
  private String awsBucketName;

  @Value("${AWS_BUCKET_REGION}")
  private String awsBucketRegion;

  @Value("${AWS_PUBLIC_KEY}")
  private String awsPublicKey;

  @Value("${AWS_SECRET_KEY_ENV}")
  private String awsSecretKey;

  @Value("${AWS_SUBFOLDER}")
  private String awsSubfolder;

  @Value("${AWS_IMAGE_URL}")
  private String awsImageUrl;

  @Value("${AWS_ENDPOINT}")
  private String awsEndpoint;

  public String getAwsBucketName() {
    return awsBucketName;
  }

  public String getAwsBucketRegion() {
    return awsBucketRegion;
  }

  public String getAwsPublicKey() {
    return awsPublicKey;
  }

  public String getAwsSecretKey() {
    return awsSecretKey;
  }

  public String getAwsSubfolder() {
    return awsSubfolder;
  }

  public String getAwsImageUrl() {
    return awsImageUrl;
  }

  public String getAwsEndpoint() {
    return awsEndpoint;
  }
}
