package com.gcasey.spotfinder.features.songs.models;

import se.michaelthelin.spotify.model_objects.specification.ArtistSimplified;

public record Artist(String id,
                     String name,
                     String href
) {
    public static Artist from(ArtistSimplified artist) {
        return new Artist(
                artist.getId(),
                artist.getName(),
                artist.getHref()
        );
    }
}
