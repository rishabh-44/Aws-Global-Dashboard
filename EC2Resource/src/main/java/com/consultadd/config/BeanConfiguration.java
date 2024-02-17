package com.consultadd.config;

import com.google.gson.Gson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ec2.Ec2Client;

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
    public EC2ClientRegionMapper regionMapper() {
        Map<Region, Ec2Client> clientMap = Region.regions()
                .stream().collect(Collectors.toMap(Function.identity(), this::getEc2Client));
        return new EC2ClientRegionMapper(clientMap);
    }

    public Ec2Client getEc2Client(Region region) {
        return Ec2Client.builder().region(region).build();
    }
}
