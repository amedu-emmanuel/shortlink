package com.shortlink.util;

import java.net.MalformedURLException;
import java.net.URL;

public class UrlValidator {

    public static boolean isValid(String urlStr) {
        try {
            new URL(urlStr);
            return true;
        } catch (MalformedURLException e) {
            return false;
        }
    }
}