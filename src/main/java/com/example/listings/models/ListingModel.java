package com.example.listings.models;


import com.example.listings.generated.types.Amenity;
import com.example.listings.generated.types.Listing;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
public class ListingModel extends Listing {

    public ListingModel(String id, String title, String description, Integer numOfBeds, Double costPerNight, Boolean closedForBookings, List<Amenity> amenities) {
        super(id, title,description, numOfBeds, costPerNight, closedForBookings, amenities);
        this.setHash();
    }

    @Override
    public String toString() {
        return "Listing{" + "id='" + this.getId() + "'," +"title='" + this.getTitle() + "'," + "hash='" + this.getHash() + "'," +"description='" + this.getDescription() + "'," +"numOfBeds='" + this.getNumOfBeds() + "'," +"costPerNight='" + this.getCostPerNight() + "'," +"closedForBookings='" + this.getClosedForBookings() + "'," +"amenities='" + this.getAmenities() + "'" +"}";
    }
    public String hash;

    public String getHash() {
        return this.hash;
    }

    public void setHash() {
        String titleAndId = this.getTitle() + this.getId();
        System.out.println("titleAndId " + this.getTitle());
        this.hash = titleAndId.replaceAll("\\s+", "");
    }
}
