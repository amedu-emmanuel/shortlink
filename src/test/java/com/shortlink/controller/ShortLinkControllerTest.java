package com.shortlink.controller;

import com.shortlink.dto.DecodeRequest;
import com.shortlink.dto.EncodeRequest;
import com.shortlink.model.ShortLink;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@TestConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ShortLinkControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String getBaseUrl() {
        return "http://localhost:" + port + "/api";
    }

    @Test
    public void testEncodeAndDecodeFlow() {
        EncodeRequest encodeRequest = new EncodeRequest("https://example.com/test-url");
        ResponseEntity<ShortLink> encodeResponse = restTemplate.postForEntity(
                getBaseUrl() + "/encode",
                encodeRequest,
                ShortLink.class
        );

        assertThat(encodeResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        ShortLink shortLink = encodeResponse.getBody();
        assertThat(shortLink).isNotNull();
        assertThat(shortLink.getShortUrl()).isNotBlank();

        DecodeRequest decodeRequest = new DecodeRequest(shortLink.getShortUrl());
        ResponseEntity<String> decodeResponse = restTemplate.postForEntity(
                getBaseUrl() + "/decode",
                decodeRequest,
                String.class
        );

        assertThat(decodeResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(decodeResponse.getBody()).contains("\"originalUrl\":\"https://example.com/test-url\"");
    }

    @Test
    public void testEncodeWithInvalidUrl() {
        EncodeRequest encodeRequest = new EncodeRequest(""); // empty URL

        ResponseEntity<String> response = restTemplate.postForEntity(
                getBaseUrl() + "/encode",
                encodeRequest,
                String.class
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testDecodeWithInvalidShortUrl() {
        DecodeRequest decodeRequest = new DecodeRequest("http://short.est/invalid123");

        ResponseEntity<String> response = restTemplate.postForEntity(
                getBaseUrl() + "/decode",
                decodeRequest,
                String.class
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void testDecodeWithEmptyInput() {
        DecodeRequest decodeRequest = new DecodeRequest("");

        ResponseEntity<String> response = restTemplate.postForEntity(
                getBaseUrl() + "/decode",
                decodeRequest,
                String.class
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }
}
