package com.shortlink.model;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ShortLink {
    private String originalUrl;
    private String shortUrl;
    private String urlPath;
    private LocalDateTime createdAt;
    private int visitCount;
}
