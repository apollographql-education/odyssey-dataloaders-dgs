package com.example.listings.models;


import com.example.listings.generated.types.Amenity;
import com.example.listings.generated.types.Listing;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
public class ListingModel extends Listing {
}
