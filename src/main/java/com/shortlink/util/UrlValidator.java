package com.shortlink.util;

import java.net.HttpURLConnection;
import java.net.URL;

public class UrlValidator {

    public static boolean isValid(String urlStr) {
        try {
            URL url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);  // Set timeout (5 seconds)
            connection.setReadTimeout(5000);     // Set read timeout (5 seconds)
            connection.connect();
            int responseCode = connection.getResponseCode();
            return responseCode == 200;  // Check if URL returns HTTP 200 (OK)
        } catch (Exception e) {
            return false;  // If exception occurs, URL is invalid or unreachable
        }
    }
}
