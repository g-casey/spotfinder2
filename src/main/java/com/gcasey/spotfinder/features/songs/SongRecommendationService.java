package com.gcasey.spotfinder.features.songs;

import com.gcasey.spotfinder.features.songs.models.Song;
import com.gcasey.spotfinder.infrastructure.SpotifyRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/songs")
public class SongRecommendationService {

    private final SpotifyRequestService spotifyRequestService;

    @Autowired
    public SongRecommendationService(SpotifyRequestService spotifyRequestService) {
        this.spotifyRequestService = spotifyRequestService;
    }

    @GetMapping("/recommendations")
    public List<Song> getRecommendedSongs(
            @RequestParam Optional<List<String>> genres,
            @RequestParam Optional<List<String>> artists,
            @RequestParam Optional<List<String>> tracks,
            @RequestParam Optional<String> popularity
    ) {
        return spotifyRequestService.getRecommendations(
                genres.orElse(List.of("pop")),
                artists.orElse(List.of()),
                tracks.orElse(List.of()),
                Popularity.valueOf(popularity.orElse("high").toUpperCase()),
                20);
    }
}
