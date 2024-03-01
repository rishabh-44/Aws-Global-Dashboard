package com.consultadd.dto;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;
import java.util.Map;

@Data
@Builder
public class IAMUserResponse {
    private String path;
    private String userName;
    private String userId;
    private String arn;
    private Instant createDate;
    private Instant passwordLastUsed;
    private List<AccessKey> accessKeys;
    private Map<String, String> permissionsBoundary;
    private Map<String, String> tags;
}
