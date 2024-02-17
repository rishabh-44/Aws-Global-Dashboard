package com.consultadd.service;

import com.consultadd.service.client.EC2Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.ec2.Ec2Client;
import software.amazon.awssdk.services.ec2.model.DescribeInternetGatewaysResponse;
import software.amazon.awssdk.services.ec2.model.DescribeNatGatewaysResponse;
import software.amazon.awssdk.services.ec2.model.DescribeRouteTablesResponse;
import software.amazon.awssdk.services.ec2.model.DescribeSecurityGroupsResponse;
import software.amazon.awssdk.services.ec2.model.DescribeSubnetsResponse;
import software.amazon.awssdk.services.ec2.model.DescribeVpcsResponse;
import software.amazon.awssdk.services.ec2.model.InternetGateway;
import software.amazon.awssdk.services.ec2.model.NatGateway;
import software.amazon.awssdk.services.ec2.model.RouteTable;
import software.amazon.awssdk.services.ec2.model.SecurityGroup;
import software.amazon.awssdk.services.ec2.model.Subnet;
import software.amazon.awssdk.services.ec2.model.Vpc;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NetworkResourceService {
    EC2Client client;

    @Autowired
    public NetworkResourceService(EC2Client client) {
        this.client = client;
    }

    public List<NatGateway> listAllNatGateways() {
        return client.callApi(Ec2Client::describeNatGateways, DescribeNatGatewaysResponse::natGateways, 1);
    }

    public List<InternetGateway> listAllInternetGateways() {
        return client.callApi(Ec2Client::describeInternetGateways, DescribeInternetGatewaysResponse::internetGateways, 1);
    }

    @Cacheable("vpcs")
    public List<Vpc> listAllVPC() {
        return client.callApi(Ec2Client::describeVpcs, DescribeVpcsResponse::vpcs, 1);
    }

    public List<Subnet> listAllSubnets() {
        return client.callApi(Ec2Client::describeSubnets, DescribeSubnetsResponse::subnets, 1);
    }

    public List<Vpc> listAllNonDefaultVPC() {
        return listAllVPC().parallelStream()
                .filter(vpc -> !vpc.isDefault())
                .collect(Collectors.toList());
    }

    @Cacheable("securityGroups")
    public List<SecurityGroup> listAllSecurityGroups() {
        return client.callApi(Ec2Client::describeSecurityGroups, DescribeSecurityGroupsResponse::securityGroups, 1);
    }

    public List<RouteTable> listAllRouteTables() {
        return client.callApi(Ec2Client::describeRouteTables, DescribeRouteTablesResponse::routeTables, 1);
    }
}
