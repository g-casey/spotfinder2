package com.gcasey.spotfinder.infrastructure;

import com.gcasey.spotfinder.features.songs.Popularity;
import com.gcasey.spotfinder.features.songs.models.Song;
import com.gcasey.spotfinder.infrastructure.exceptions.CustomException;
import com.neovisionaries.i18n.CountryCode;
import org.apache.hc.core5.http.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.exceptions.detailed.UnauthorizedException;
import se.michaelthelin.spotify.model_objects.specification.TrackSimplified;
import se.michaelthelin.spotify.requests.data.AbstractDataRequest;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class SpotifyRequestService {

    private final SpotifyApi client;

    @Autowired
    public SpotifyRequestService(SpotifyApi client) {
        this.client = client;
    }

    // TODO make async
    private <T> T makeRequest(AbstractDataRequest<T> request) {
        try {
            return request.execute();
        } catch (UnauthorizedException e) {
            try {
                String token = client.clientCredentials().build().execute().getAccessToken();
                client.setAccessToken(token);
                return request.execute();
            } catch (IOException | ParseException | SpotifyWebApiException ex) {
                throw new CustomException("Failed to authenticate with Spotify", HttpStatus.BAD_GATEWAY);
            }
        } catch (IOException | ParseException | SpotifyWebApiException e) {
            throw new CustomException("Spotify Api Request Failed", HttpStatus.BAD_GATEWAY);
        }
    }

    public Song getTrack(String id) {
        return Song.from(makeRequest(client.
                getTrack(id)
                .market(CountryCode.US).build()
        ));
    }


    public List<Song> getRecommendations(List<String> genres, List<String> songs, List<String> artists, Popularity popularity, int amount) {
        return Arrays.stream(makeRequest(client.getRecommendations()
                        .market(CountryCode.US)
                        .seed_genres(String.join(",", genres))
                        .seed_artists(String.join(",", artists))
                        .seed_tracks(String.join(",", songs))
                        .min_popularity(popularity.getMin())
                        .max_popularity(popularity.getMax())
                        .limit(amount)
                        .build())
                        .getTracks())
                .filter(track -> track.getPreviewUrl() != null)
                .map(TrackSimplified::getId)
                .map(this::getTrack)
                .toList();
    }

}
