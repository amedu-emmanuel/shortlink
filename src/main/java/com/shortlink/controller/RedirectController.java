package com.shortlink.controller;

import com.shortlink.model.ShortLink;
import com.shortlink.service.ShortLinkService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class RedirectController {

    private final ShortLinkService service;

    public RedirectController(ShortLinkService service) {
        this.service = service;
    }

    @GetMapping("/{urlPath}")
    public ResponseEntity<?> redirect(@PathVariable String urlPath) {
        ShortLink link = service.incrementVisitCount(urlPath);
        if (link != null) {
            return ResponseEntity.status(302)
                    .header(HttpHeaders.LOCATION, link.getOriginalUrl())
                    .build();
        }
        return ResponseEntity.notFound().build();
    }
}