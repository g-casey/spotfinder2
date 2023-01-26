package com.gcasey.spotfinder.features.songs.models;

import se.michaelthelin.spotify.model_objects.specification.Track;

import java.util.Arrays;
import java.util.List;

public record Song(String id,
                   String name,
                   List<Artist> artists,
                   String href,
                   Album album,
                   boolean isPlayable,
                   int popularity,
                   String externalUrl,
                   String previewUrl) {

    public static Song from(Track track) {
        return new Song(
                track.getId(),
                track.getName(),
                Arrays.stream(track.getArtists()).map(Artist::from).toList(),
                track.getHref(),
                new Album(track.getAlbum().getName(), track.getAlbum().getImages()[0].getUrl()),
                track.getIsPlayable(),
                track.getPopularity(),
                track.getExternalUrls().get("spotify"),
                track.getPreviewUrl()
        );
    }
}
