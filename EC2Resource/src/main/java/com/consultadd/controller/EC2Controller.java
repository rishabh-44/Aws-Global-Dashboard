package com.consultadd.controller;

import com.consultadd.service.EC2Service;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/EC2")
@CrossOrigin("*")
public class EC2Controller {

    @Autowired
    EC2Service service;
    @Autowired
    Gson gson;

    @GetMapping("/Instances")
    public ResponseEntity<String> listAllInstance() {
        return ResponseEntity.ok(gson.toJson(service.listAllInstance()));
    }

    @GetMapping("/Images")
    public ResponseEntity<String> listAllImages() {
        return ResponseEntity.ok(gson.toJson(service.listAllImages()));
    }

    @GetMapping("/Eip")
    public ResponseEntity<String> listAllEIPs() {
        return ResponseEntity.ok(gson.toJson(service.listAllEIp()));
    }

    @GetMapping("/Snapshots")
    public ResponseEntity<String> listAllSnapshots() {
        return ResponseEntity.ok(gson.toJson(service.listAllSnapshots()));
    }

    @GetMapping("/LaunchTemplates")
    public ResponseEntity<String> listAllLaunchTemplates() {
        return ResponseEntity.ok(gson.toJson(service.listAllLaunchTemplates()));
    }

    @GetMapping("/KeyPairs")
    public ResponseEntity<String> listAllKeyPairs() {
        return ResponseEntity.ok(gson.toJson(service.listAllKeyPairs()));
    }


    @GetMapping("/reservations")
    public ResponseEntity<String> listAllReservations() {
        return ResponseEntity.ok(gson.toJson(service.listAllReservations()));
    }
}
