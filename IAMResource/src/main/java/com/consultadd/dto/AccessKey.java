package com.consultadd.dto;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class AccessKey {

    private String accessKeyId;
    private String status;
    private Instant createDate;
}
