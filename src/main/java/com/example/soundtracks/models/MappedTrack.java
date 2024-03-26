package com.example.soundtracks.models;
import com.example.soundtracks.generated.types.Track;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.JsonNode;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MappedTrack extends Track {

    private String artistId;

    // When tracks are returned as part of a playlist, their data is nested below a "track" JSON property.
    // This method reaches into "track" and sets the appropriate properties.
    @JsonSetter("track")
    public void setTrackProperties(JsonNode trackObject) {
        this.mapArtistId(trackObject.get("artists"));
        this.setId(trackObject.get("id").asText());
        this.setName(trackObject.get("name").asText());
        this.setDurationMs(trackObject.get("duration_ms").asInt());
        this.setExplicit(trackObject.get("explicit").asBoolean());
        this.setUri(trackObject.get("uri").asText());
    }

    // A track requested by itself does not nest its properties; consequently the majority can be set on the Track instance automatically.
    // This method is used when we need to map through the "artists" JSON key on this object and set the artistID property on the Track.
    @JsonSetter("artists")
    public void mapArtistId(JsonNode artistsArray) {
        this.setArtistId(artistsArray.get(0).get("id").asText());
    }

    public Track getTrack() {
        return this;
    }

    public void setArtistId(String artistId) {
        this.artistId = artistId;
    }

    public String getArtistId() {
        return this.artistId;
    }
}