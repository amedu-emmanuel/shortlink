package com.shortlink.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EncodeRequest {

    @NotBlank(message = "URL cannot be blank")
    private String url;
}
