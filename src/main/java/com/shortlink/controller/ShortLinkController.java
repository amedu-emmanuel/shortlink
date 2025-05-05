package com.shortlink.controller;

import com.shortlink.dto.DecodeRequest;
import com.shortlink.dto.EncodeRequest;
import com.shortlink.model.ShortLink;
import com.shortlink.dto.ErrorResponse;
import com.shortlink.service.ShortLinkService;
import com.shortlink.util.UrlValidator;  // Import UrlValidator
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ShortLinkController {

    private final ShortLinkService service;

    public ShortLinkController(ShortLinkService service) {
        this.service = service;
    }

    @PostMapping("/encode")
    public ResponseEntity<?> encode(@RequestBody @Valid EncodeRequest request) {
        System.out.println("Received URL: " + request.getUrl());
        // Validate the URL before processing
        if (!UrlValidator.isValid(request.getUrl())) {
            return ResponseEntity.badRequest().body(new ErrorResponse("Invalid or unreachable URL"));
        }

        ShortLink shortLink = service.encode(request.getUrl());
        return ResponseEntity.ok(shortLink);
    }

    @PostMapping("/decode")
    public ResponseEntity<Map<String, String>> decode(@RequestBody @Valid DecodeRequest request) {
        String originalUrl = service.decode(request.getShortUrl());

        if (originalUrl != null) {
            Map<String, String> response = new HashMap<>();
            response.put("originalUrl", originalUrl);
            return ResponseEntity.ok(response);
        } else {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Short URL not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
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