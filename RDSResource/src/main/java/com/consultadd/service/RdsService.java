package com.consultadd.service;

import com.consultadd.client.RdsClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.rds.model.DBInstance;

import java.util.List;
import java.util.Map;

@Service
public class RdsService {

  RdsClient rdsClient;

  @Autowired
  public RdsService(RdsClient rdsClient) {
    this.rdsClient = rdsClient;
  }

  public Map<String, List<DBInstance>> getAllRdsInstances() {
    return rdsClient.getAllRdsInstances();
  }

  public List<DBInstance> getRdsInstancesByRegion(String region) {
    return rdsClient.getRdsInstancesByRegion(region);
  }

}
