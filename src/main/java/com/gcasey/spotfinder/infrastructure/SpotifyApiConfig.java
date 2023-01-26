package com.gcasey.spotfinder.infrastructure;

import org.apache.hc.core5.http.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;

import java.io.IOException;

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
        SpotifyApi api = new SpotifyApi.Builder()
                .setClientId(clientId)
                .setClientSecret(clientSecret)
                .build();

        try {
            String token = api.clientCredentials().build().execute().getAccessToken();
            api.setAccessToken(token);
        } catch (IOException | ParseException | SpotifyWebApiException e) {
            //throw new CustomException("Failed to authenticate with Spotify", HttpStatus.BAD_GATEWAY);
        }
        return api;
    }
}
