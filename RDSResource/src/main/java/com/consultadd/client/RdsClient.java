package com.consultadd.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.rds.model.DBInstance;
import software.amazon.awssdk.services.rds.model.DescribeDbInstancesResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class RdsClient {

  Logger log = LoggerFactory.getLogger(RdsClient.class);

  private RdsClientRegionMapper clientRegionMapper;

  @Autowired
  public RdsClient(RdsClientRegionMapper rdsClientRegionMapper) {
    this.clientRegionMapper = rdsClientRegionMapper;
  }

  public Map<String, List<DBInstance>> getAllRdsInstances() {
    Map<String, List<DBInstance>> resultMap = new HashMap<>();
    for (Region region : clientRegionMapper.getRegionClientMap().keySet()) {
      try {
        DescribeDbInstancesResponse dbInstancesResponse = clientRegionMapper.getRegionClientMap().get(region).describeDBInstances();
        if (dbInstancesResponse.hasDbInstances()) {
          resultMap.put(region.id(), dbInstancesResponse.dbInstances());
        }
      } catch (Exception exception) {
        log.error("exception in fetching RdsInstances for Region " + region + " : " + exception.toString());
        continue;
      }
    }
    return resultMap;
  }

  public List<DBInstance> getRdsInstancesByRegion(String region) {
    try {
      return clientRegionMapper.getRegionClientMap().get(Region.of(region.toLowerCase())).describeDBInstances().dbInstances();
    } catch (Exception exception) {
      log.error("exception in fetching RdsInstances for Region " + region + " : " + exception.toString());
      return List.of();
    }
  }

}
