package com.consultadd.service;

import com.consultadd.service.client.EC2Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.ec2.Ec2Client;
import software.amazon.awssdk.services.ec2.model.Address;
import software.amazon.awssdk.services.ec2.model.DescribeAddressesResponse;
import software.amazon.awssdk.services.ec2.model.DescribeImagesResponse;
import software.amazon.awssdk.services.ec2.model.DescribeInstancesResponse;
import software.amazon.awssdk.services.ec2.model.DescribeKeyPairsResponse;
import software.amazon.awssdk.services.ec2.model.DescribeLaunchTemplatesResponse;
import software.amazon.awssdk.services.ec2.model.DescribeSnapshotsResponse;
import software.amazon.awssdk.services.ec2.model.Image;
import software.amazon.awssdk.services.ec2.model.Instance;
import software.amazon.awssdk.services.ec2.model.KeyPairInfo;
import software.amazon.awssdk.services.ec2.model.LaunchTemplate;
import software.amazon.awssdk.services.ec2.model.Reservation;
import software.amazon.awssdk.services.ec2.model.Snapshot;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EC2Service {
    EC2Client client;

    @Autowired
    public EC2Service(EC2Client client) {
        this.client = client;
    }

    public List<Instance> listAllInstance() {

        return listAllReservations().parallelStream()
                .map(Reservation::instances)
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    @Cacheable("reservations")
    public List<Reservation> listAllReservations() {
        return client.callApi(Ec2Client::describeInstances, DescribeInstancesResponse::reservations, 1);
    }

    @Cacheable("keypairs")
    public List<KeyPairInfo> listAllKeyPairs() {
        return client.callApi(Ec2Client::describeKeyPairs, DescribeKeyPairsResponse::keyPairs, 1);
    }

    @Cacheable("launchTemplates")
    public List<LaunchTemplate> listAllLaunchTemplates() {
        return client.callApi(Ec2Client::describeLaunchTemplates, DescribeLaunchTemplatesResponse::launchTemplates, 1);
    }

    @Cacheable("snapshots")
    public List<Snapshot> listAllSnapshots() {
        return client.callApi(Ec2Client::describeSnapshots, DescribeSnapshotsResponse::snapshots, 1);
    }

    public List<Image> listAllImages() {
        return client.callApi(Ec2Client::describeImages, DescribeImagesResponse::images, 1);
    }

    public List<Address> listAllEIp() {
        return client.callApi(Ec2Client::describeAddresses, DescribeAddressesResponse::addresses, 1);
    }


}
