package com.gcasey.spotfinder.features.songs;

import com.gcasey.spotfinder.infrastructure.SpotifyRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongService {

    private final SpotifyRequestService spotifyRequestService;

    @Autowired
    public SongService(SpotifyRequestService spotifyRequestService) {
        this.spotifyRequestService = spotifyRequestService;
    }

    public List<String> getGenres() {
        return spotifyRequestService.getAllGenres();
    }
}
