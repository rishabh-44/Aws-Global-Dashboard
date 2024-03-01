package com.consultadd.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.iam.IamClient;

@Configuration
public class BeanConfiguration {

    @Bean
    public IamClient iamClient() {
        return IamClient.builder().region(Region.AWS_GLOBAL).build();
    }

}
