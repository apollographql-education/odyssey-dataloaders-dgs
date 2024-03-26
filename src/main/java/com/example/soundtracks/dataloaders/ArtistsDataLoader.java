package com.example.soundtracks.dataloaders;

import com.example.soundtracks.datasources.SpotifyClient;
import com.example.soundtracks.models.MappedArtist;
import com.netflix.graphql.dgs.DgsDataLoader;
import org.dataloader.BatchLoader;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import org.dataloader.Try;

@DgsDataLoader(name = "artists")
public class ArtistsDataLoader implements BatchLoader<String, MappedArtist> {

    @Autowired
    SpotifyClient spotifyClient;

    @Override
    public CompletionStage<List<MappedArtist>> load(List<String> artistIds) {
        return CompletableFuture.supplyAsync(() -> spotifyClient.multipleArtistsRequest(artistIds));
    }

    // ERROR HANDLING

}
