package com.consultadd.service.client;

import com.consultadd.config.EC2ClientRegionMapper;
import com.consultadd.utils.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.ec2.Ec2Client;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class EC2Client {

    private final EC2ClientRegionMapper mapper;

    @Autowired
    public EC2Client(EC2ClientRegionMapper mapper) {
        this.mapper = mapper;
    }

    public  <T, R> List<R> callApi(Function<Ec2Client, T> function1, Function<T, List<R>> function2, int retries) {
        return mapper.getClientMap().values().parallelStream()
                .map(e -> Retry.retry(retries, () -> function1.apply(e)))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(function2)
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }
}
