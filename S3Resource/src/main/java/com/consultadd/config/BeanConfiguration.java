package com.consultadd.config;

import com.google.gson.Gson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class BeanConfiguration {

  @Bean
  public Gson getGson() {
    return new Gson().newBuilder().create();
  }

  @Bean
  public S3Client getS3Client() {
    return S3Client.builder().build();
  }
}
