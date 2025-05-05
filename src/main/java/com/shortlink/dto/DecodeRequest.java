package com.shortlink.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DecodeRequest {
    @NotBlank(message = "Short URL cannot be blank")
    private String shortUrl;
}
