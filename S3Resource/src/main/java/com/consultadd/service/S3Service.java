package com.consultadd.service;

import com.consultadd.client.S3Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class S3Service {

  S3Client s3Client;

  @Autowired
  public S3Service(S3Client s3Client) {
    this.s3Client = s3Client;
  }

  public List<S3Client.S3Bucket> getAllBuckets() {
    return s3Client.getAllBuckets();
  }

}
