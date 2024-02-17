package com.consultadd.controller;

import com.consultadd.service.VolumeService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/volume")
public class VolumeController {

    @Autowired
    VolumeService service;
    @Autowired
    Gson gson;

    @GetMapping
    public ResponseEntity getAllVolumes() {
        return ResponseEntity.ok(gson.toJson(service.listAllVolumes()));
    }
}
