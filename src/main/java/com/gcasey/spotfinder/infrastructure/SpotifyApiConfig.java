package com.gcasey.spotfinder.infrastructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import se.michaelthelin.spotify.SpotifyApi;

@Configuration
public class SpotifyApiConfig {

    private final String clientId;
    private final String clientSecret;

    @Autowired
    public SpotifyApiConfig(
            @Value("${spotify.client_id}")
            String clientId,
            @Value("${spotify.client_secret}")
            String clientSecret) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    @Bean
    public SpotifyApi spotifyApi() {
        return new SpotifyApi.Builder()
                .setClientId(clientId)
                .setClientSecret(clientSecret)
                .build();
    }
}
