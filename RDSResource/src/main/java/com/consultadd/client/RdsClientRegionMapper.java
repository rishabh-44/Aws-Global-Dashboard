package com.consultadd.client;

import lombok.Getter;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.rds.RdsClient;

import java.util.Map;

@Getter
public class RdsClientRegionMapper {

  private Map<Region, RdsClient> regionClientMap;

  public RdsClientRegionMapper(Map<Region, RdsClient> regionClientMap) {
    this.regionClientMap = regionClientMap;
  }

}
