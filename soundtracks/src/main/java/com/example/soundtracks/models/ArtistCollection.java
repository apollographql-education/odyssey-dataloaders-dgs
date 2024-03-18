package com.example.soundtracks.models;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

public class ArtistCollection {

    List<MappedArtist> artists;

    @JsonSetter ("artists")
    public void setArtists(JsonNode artists) throws IOException {
        this.artists = new ObjectMapper().readValue(artists.traverse(), new TypeReference<>() {
        });
    }
    public List<MappedArtist> getArtists() {
        return this.artists;
    }

}

