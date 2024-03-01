package com.consultadd.controller;

import com.consultadd.client.S3Client;
import com.consultadd.service.S3Service;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/s3")
public class S3Controller {

  @Autowired
  private S3Service s3Service;

  @Autowired
  private Gson gson;

  @GetMapping("/buckets")
  public ResponseEntity<List<S3Client.S3Bucket>> getAllBuckets() {
    List<S3Client.S3Bucket> response = s3Service.getAllBuckets();
    return ResponseEntity.ok(response);
  }

}
