package com.gcasey.spotfinder.features.songs;

import com.neovisionaries.i18n.CountryCode;
import org.apache.hc.core5.http.ParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.TrackSimplified;

import java.io.IOException;

@RestController
@RequestMapping("/songs")
public class SongRecommendationService {

    private final SpotifyApi client;

    public SongRecommendationService(SpotifyApi client) {
        this.client = client;
    }

    @GetMapping("/popular")
    public TrackSimplified[] getTopSongs() throws IOException, ParseException, SpotifyWebApiException {
        return client.getRecommendations()
                .market(CountryCode.US)
                .seed_genres("pop")
                .limit(10)
                .build()
                .execute().getTracks();
    }
}
