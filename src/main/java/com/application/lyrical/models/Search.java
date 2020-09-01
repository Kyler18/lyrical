package com.application.lyrical.models;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

@Component
public class Search {
    @Value("${rapidapi.genius.url}")
    private String geniusUrl;
    @Value("${rapidapi.key.name}")
    private String apiKeyName;
    @Value("${rapidapi.key.value}")
    private String apiKeyValue;
    @Value("${rapidapi.host.name}")
    private String hostName;
    @Value("${rapidapi.host.genius.value}")
    private String hostValue;

    public Search() throws UnsupportedEncodingException {
    }
    public String getGeniusUrl() {
        return geniusUrl;
    }

    public void setGeniusUrl(String geniusUrl) {
        this.geniusUrl = geniusUrl;
    }

    public String getApiKeyName() {
        return apiKeyName;
    }

    public void setApiKeyName(String apiKeyName) {
        this.apiKeyName = apiKeyName;
    }

    public String getApiKeyValue() {
        return apiKeyValue;
    }

    public void setApiKeyValue(String apiKeyValue) {
        this.apiKeyValue = apiKeyValue;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getHostValue() {
        return hostValue;
    }

    public void setHostValue(String hostValue) {
        this.hostValue = hostValue;
    }

}
