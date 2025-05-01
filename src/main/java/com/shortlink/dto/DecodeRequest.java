package com.shortlink.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DecodeRequest {
    @NotBlank(message = "Short URL cannot be blank")
    private String shortUrl;
}
