package com.example.soundtracks.datasources;

import com.example.soundtracks.models.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class SpotifyClient {
    private static final String SPOTIFY_API_URL = "http://localhost:5001/v1";
    private final RestClient client = RestClient.builder().baseUrl(SPOTIFY_API_URL).build();

    public PlaylistCollection featuredPlaylistsRequest() {
        return client
                .get()
                .uri("/browse/featured-playlists")
                .retrieve()
                .body(PlaylistCollection.class);
    }

    public MappedPlaylist playlistRequest(String playlistId) {
        return client
                .get()
                .uri("/playlists/{playlist_id}", playlistId)
                .retrieve()
                .body(MappedPlaylist.class);
    }

    public MappedTrack trackRequest(String trackId) {
        return client
                .get()
                .uri("/tracks/{track_id}", trackId)
                .retrieve()
                .body(MappedTrack.class);
    }

    public MappedArtist artistRequest(String artistId) {
        System.out.println("I am making a request to the artists endpoint for " + artistId);
        return client
                .get()
                .uri("/artists/{artist_id}", artistId)
                .retrieve()
                .body(MappedArtist.class);
    }

    public Snapshot addItemsToPlaylist(String playlistId, String uris) {
        return client
                .post()
                .uri(uriBuilder -> uriBuilder
                        .path("/playlists/{playlist_id}/tracks")
                        .queryParam("uris", uris)
                        .build(playlistId))
                .retrieve()
                .body(Snapshot.class);
    }

    public PlaylistCollection search(String term) {
        return client
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/search")
                        .queryParam("q", term)
                        .queryParam("type", "playlist")
                        .build())
                .retrieve()
                .body(PlaylistCollection.class);
    }
}
