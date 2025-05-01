package com.shortlink.service;

import com.shortlink.model.ShortLink;
import com.shortlink.repository.ShortLinkRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

@Service
public class ShortLinkService {

    private final ShortLinkRepository repository = new ShortLinkRepository();
    private static final String BASE_URL = "http://short.est/";

    public ShortLink encode(String originalUrl) {
        String uuid = UUID.randomUUID().toString().substring(0, 6);
        String shortUrl = BASE_URL + uuid;

        ShortLink link = new ShortLink();
        link.setOriginalUrl(originalUrl);
        link.setShortUrl(shortUrl);
        link.setUrlPath(uuid);
        link.setCreatedAt(LocalDateTime.now());
        link.setVisitCount(0);

        repository.save(link);
        return link;
    }

    public String decode(String shortUrl) {
        ShortLink link = repository.findByShortUrl(shortUrl);
        if (link != null) {
            return link.getOriginalUrl();
        }
        return null;
    }

    public ShortLink getByPath(String path) {
        return repository.findByPath(path);
    }

    public List<ShortLink> getAll() {
        return repository.findAll();
    }

    public ShortLink incrementVisitCount(String path) {
        ShortLink link = repository.findByPath(path);
        if (link != null) {
            link.setVisitCount(link.getVisitCount() + 1);
        }
        return link;
    }
}