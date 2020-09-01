package com.application.lyrical.models;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Song {
    @Value("${musix.url}")
    private String musixUrl;
    @Value("${musix.key.name}")
    private String apiKeyName;
    @Value("${musix.key.value}")
    private String apiKeyValue;
    @Value("${musix.host.name}")
    private String hostName;
    @Value("${musix.host.value}")
    private String hostValue;

    public String getMusixUrl() {
        return musixUrl;
    }

    public void setMusixUrl(String musixUrl) {
        this.musixUrl = musixUrl;
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
