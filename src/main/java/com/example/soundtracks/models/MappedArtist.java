package com.example.soundtracks.models;
import com.example.soundtracks.generated.types.Artist;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.JsonNode;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MappedArtist extends Artist {

    @JsonSetter("artists")
    public void setArtistProperties(JsonNode artistsObject) {
        JsonNode artist = artistsObject.get(0);

        if (artist != null) {
            this.setId(artist.get("id").asText());
            this.setName(artist.get("name").asText());
            this.setPopularity(artist.get("popularity").asInt());
            this.setUri(artist.get("uri").asText());
        }
    }
}
