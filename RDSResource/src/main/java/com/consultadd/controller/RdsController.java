package com.consultadd.controller;

import com.consultadd.service.RdsService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import software.amazon.awssdk.services.rds.model.DBInstance;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/rds")
public class RdsController {

  @Autowired
  private RdsService rdsService;

  @Autowired
  private Gson gson;

  @GetMapping("/instances")
  public ResponseEntity<String> getAllRdsInstances() {
    Map<String, List<DBInstance>> response = rdsService.getAllRdsInstances();
    return ResponseEntity.ok(gson.toJson(response));
  }

  @GetMapping("/instanceByRegion")
  public ResponseEntity<String> getRdsInstancesByRegion(@RequestParam String region) {
    List<DBInstance> response = rdsService.getRdsInstancesByRegion(region);
    return ResponseEntity.ok(gson.toJson(response));
  }

}
