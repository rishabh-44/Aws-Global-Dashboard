package com.consultadd.controller;

import com.consultadd.service.NetworkResourceService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import software.amazon.awssdk.services.ec2.model.Subnet;

import java.util.List;

@RestController
@RequestMapping("/network")
public class NetworkResourceController {
    @Autowired
    NetworkResourceService service;
    @Autowired
    Gson gson;

    @GetMapping("/sg")
    public String getAllSecurityGroups() {
        return gson.toJson(service.listAllSecurityGroups());
    }

    @GetMapping("/vpc")
    public String getAllVPCs(@RequestParam("non_default") boolean isNonDefault) {
        if (isNonDefault) {
            return gson.toJson(service.listAllNonDefaultVPC());
        }
        return gson.toJson(service.listAllVPC());
    }

    @GetMapping("/subnet")
    public String listAllSubnets() {
        return gson.toJson(service.listAllSubnets());
    }

    @GetMapping("/nat")
    public String getAllNATs() {
        return gson.toJson(service.listAllNatGateways());
    }

    @GetMapping("/ig")
    public String getAllIGs() {
        return gson.toJson(service.listAllInternetGateways());
    }

    @GetMapping("/rt")
    public String getAllRouteTables() {
        return gson.toJson(service.listAllRouteTables());
    }
}
