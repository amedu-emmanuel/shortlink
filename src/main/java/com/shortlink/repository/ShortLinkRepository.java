package com.shortlink.repository;

import com.shortlink.model.ShortLink;
import java.util.*;

public class ShortLinkRepository {

    private static final Map<String, ShortLink> store = new HashMap<>();

    public void save(ShortLink link) {
        store.put(link.getUrlPath(), link);
    }

    public ShortLink findByPath(String path) {
        return store.get(path);
    }

    public List<ShortLink> findAll() {
        return new ArrayList<>(store.values());
    }

    public ShortLink findByShortUrl(String shortUrl) {
        return store.values().stream()
                .filter(link -> link.getShortUrl().equals(shortUrl))
                .findFirst().orElse(null);
    }
}