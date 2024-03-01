package com.consultadd.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

@Component
public class S3Client {

  Logger log = LoggerFactory.getLogger(S3Client.class);

  @Autowired
  private software.amazon.awssdk.services.s3.S3Client s3Client;

  public List<S3Bucket> getAllBuckets() {
    List<S3Bucket> bucketList = new ArrayList<>();
    try {
      List<software.amazon.awssdk.services.s3.model.Bucket> buckets = s3Client.listBuckets().buckets();
      if (!buckets.isEmpty()) {
        buckets.stream().forEach(bucket -> {
          LocalDateTime creationDate = LocalDateTime.ofInstant(bucket.creationDate(), ZoneId.of(ZoneOffset.UTC.getId()));
          S3Bucket s3Bucket = new S3Bucket(bucket.name(), creationDate.toLocalDate() +" "+creationDate.toLocalTime());
          bucketList.add(s3Bucket);
        });
      }
    } catch (Exception exception) {
      log.error("exception in fetching Buckets : " + exception.toString());
    }
    return bucketList;
  }

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  public static class S3Bucket {
    private String name;
    private String creationDate;
  }

}
