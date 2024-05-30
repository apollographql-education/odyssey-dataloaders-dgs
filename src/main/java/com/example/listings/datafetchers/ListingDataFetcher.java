package com.example.listings.datafetchers;
import com.example.listings.generated.types.Amenity;
import com.example.listings.generated.types.CreateListingResponse;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.DgsQuery;
import com.example.listings.models.ListingModel;
import com.netflix.graphql.dgs.DgsMutation;

import java.io.IOException;
import java.util.List;
import com.example.listings.datasources.ListingService;
import org.springframework.beans.factory.annotation.Autowired;
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment;
import com.netflix.graphql.dgs.InputArgument;
import com.example.listings.generated.types.CreateListingInput;


@DgsComponent
public class ListingDataFetcher {

    private final ListingService listingService;

    @Autowired
    public ListingDataFetcher(ListingService listingService) {
        this.listingService = listingService;
    }
    @DgsQuery
    public List<ListingModel> featuredListings() throws IOException {
        return listingService.featuredListingsRequest();
    }

    @DgsQuery
    public ListingModel listing(@InputArgument String id) {
        return listingService.listingRequest(id);
    }

    @DgsData(parentType = "Listing")
    public List<Amenity> amenities(DgsDataFetchingEnvironment dfe) throws IOException {
        ListingModel listing = dfe.getSource();
        String id = listing.getId();
        List<Amenity> amenities = listing.getAmenities();

        if (amenities != null) {
            return amenities;
        } else {
            return listingService.amenitiesRequest(id);
        }
    }

    @DgsMutation
    public CreateListingResponse createListing(@InputArgument CreateListingInput input) {
        ListingModel createdListing = listingService.createListingRequest(input);
        CreateListingResponse response = new CreateListingResponse();
        // We can still access createdListing.getHash() here!

        if (createdListing != null) {
            response.setListing(createdListing);
            // We can no longer access response.listing.getHash() here!
            response.setCode(200);
            response.setMessage("success");
            response.setSuccess(true);


            return response;
        }

        response.setListing(null);
        response.setCode(500);
        response.setMessage("could not create listing");
        response.setSuccess(false);

        System.out.println(response.getListing());
        return response;
    }
}
