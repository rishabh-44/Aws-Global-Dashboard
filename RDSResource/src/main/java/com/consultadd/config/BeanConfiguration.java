package com.consultadd.config;

import com.consultadd.client.RdsClientRegionMapper;
import com.google.gson.Gson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.rds.RdsClient;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Configuration
public class BeanConfiguration {

  @Bean
  public Gson getGson() {
    return new Gson().newBuilder().create();
  }

  @Bean
  public RdsClientRegionMapper rdsClientRegionMapper() {
    Map<Region, RdsClient> regionRdsClientMap = Region.regions()
        .stream().collect(Collectors.toMap(Function.identity(), this::getRdsClientByRegion));
    return new RdsClientRegionMapper(regionRdsClientMap);
  }

  public RdsClient getRdsClientByRegion(Region region) {
    return RdsClient.builder().region(region).build();
  }

}
