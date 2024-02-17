package com.consultadd.config;

import lombok.Getter;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ec2.Ec2Client;

import java.util.Map;
@Getter
public class EC2ClientRegionMapper {
    private final Map<Region, Ec2Client> clientMap;

    public EC2ClientRegionMapper(Map<Region, Ec2Client> clientMap) {
        this.clientMap = clientMap;
    }

}
