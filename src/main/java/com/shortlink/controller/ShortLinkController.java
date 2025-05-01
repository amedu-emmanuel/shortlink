package com.shortlink.controller;

import com.shortlink.dto.DecodeRequest;
import com.shortlink.dto.EncodeRequest;
import com.shortlink.model.ShortLink;
import com.shortlink.service.ShortLinkService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ShortLinkController {

    private final ShortLinkService service;

    public ShortLinkController(ShortLinkService service) {
        this.service = service;
    }

    @PostMapping("/encode")
    public ResponseEntity<ShortLink> encode(@RequestBody @Valid EncodeRequest request) {
        return ResponseEntity.ok(service.encode(request.getUrl()));
    }

    @PostMapping("/decode")
    public ResponseEntity<String> decode(@RequestBody @Valid DecodeRequest request) {
        String originalUrl = service.decode(request.getShortUrl());
        return originalUrl != null ?
                ResponseEntity.ok(originalUrl) :
                ResponseEntity.notFound().build();
    }

    @GetMapping("/statistic/{urlPath}")
    public ResponseEntity<ShortLink> statistics(@PathVariable String urlPath) {
        ShortLink link = service.getByPath(urlPath);
        return link != null ? ResponseEntity.ok(link) : ResponseEntity.notFound().build();
    }

    @GetMapping("/list")
    public ResponseEntity<List<ShortLink>> list() {
        return ResponseEntity.ok(service.getAll());
    }
}