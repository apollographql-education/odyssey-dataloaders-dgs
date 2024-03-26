package com.example.soundtracks.datafetchers;

import com.example.soundtracks.datasources.SpotifyClient;
import com.example.soundtracks.models.MappedTrack;
import com.example.soundtracks.models.MappedArtist;
import com.netflix.graphql.dgs.*;

import org.dataloader.DataLoader;
import java.util.concurrent.CompletableFuture;


@DgsComponent
public class TrackDataFetcher {
    private final SpotifyClient spotifyClient;

    public TrackDataFetcher(SpotifyClient spotifyClient) {
        this.spotifyClient = spotifyClient;
    }


    @DgsQuery
    public MappedTrack track(@InputArgument String id) {
        return this.spotifyClient.trackRequest(id);
    }

    @DgsData(parentType="Track", field="artist")
    public CompletableFuture<MappedArtist> getArtist(DgsDataFetchingEnvironment dfe) {
        MappedTrack track = dfe.getSource();
        String artistId = track.getArtistId();
        DataLoader<String, MappedArtist> artistDataloader = dfe.getDataLoader("artists");
        return artistDataloader.load(artistId);
    }

}
