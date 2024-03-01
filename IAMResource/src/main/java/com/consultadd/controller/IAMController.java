package com.consultadd.controller;

import com.consultadd.dto.AccessKey;
import com.consultadd.dto.IAMUserResponse;
import com.consultadd.service.IAMService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@CrossOrigin("*")
@RequestMapping("/iam")
@RequiredArgsConstructor
public class IAMController {

    private final IAMService iamService;

    @GetMapping("/user/all")
    public ResponseEntity<List<IAMUserResponse>> getAllIamUsers() throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(iamService.listAllUsers());
    }

    @GetMapping("/accessKey")
    public ResponseEntity<List<AccessKey>> getAllAccessKeys(@RequestParam("username") String username) {
        return ResponseEntity.ok(iamService.listAccessKeys(username));
    }

}
