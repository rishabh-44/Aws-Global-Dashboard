package com.consultadd.service;

import com.consultadd.service.client.EC2Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.ec2.Ec2Client;
import software.amazon.awssdk.services.ec2.model.DescribeVolumesResponse;
import software.amazon.awssdk.services.ec2.model.Volume;

import java.util.List;

@Service
public class VolumeService {

    EC2Client client;

    @Autowired
    public VolumeService(EC2Client client) {
        this.client = client;
    }

    @Cacheable("volumes")
    public List<Volume> listAllVolumes() {
        return client.callApi(Ec2Client::describeVolumes, DescribeVolumesResponse::volumes, 1);
    }


}
