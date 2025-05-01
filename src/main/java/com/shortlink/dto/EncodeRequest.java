package com.shortlink.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EncodeRequest {
    @NotBlank(message = "URL cannot be blank")
    private String url;
}
