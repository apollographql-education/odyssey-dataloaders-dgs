package com.example.listings.datafetchers;
import com.example.listings.generated.types.Amenity;
import com.example.listings.generated.types.CreateListingResponse;
import com.example.listings.models.AmenityList;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.DgsQuery;
import com.example.listings.models.ListingModel;
import com.netflix.graphql.dgs.DgsMutation;

import java.io.IOException;
import java.util.List;
import com.example.listings.datasources.ListingService;
import graphql.execution.DataFetcherResult;
import org.springframework.beans.factory.annotation.Autowired;
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment;
import com.netflix.graphql.dgs.InputArgument;
import com.example.listings.generated.types.CreateListingInput;

import org.dataloader.DataLoader;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;


@DgsComponent
public class ListingDataFetcher {

    private final ListingService listingService;

    @Autowired
    public ListingDataFetcher(ListingService listingService) {
        this.listingService = listingService;
    }
    @DgsQuery
    public DataFetcherResult<List<ListingModel>> featuredListings() throws IOException {
        List<ListingModel> featuredListings = listingService.featuredListingsRequest();
        return DataFetcherResult.<List<ListingModel>>newResult()
                .data(featuredListings)
                .localContext("featuredListings")
                .build();
    }

    @DgsQuery
    public DataFetcherResult<ListingModel> listing(@InputArgument String id) {
        ListingModel listing = listingService.listingRequest(id);
        return DataFetcherResult.<ListingModel>newResult()
                .data(listing)
                .localContext("listing")
                .build();
    }
    @DgsData(parentType = "Listing")
    public Object amenities(DgsDataFetchingEnvironment dfe) throws IOException {
        ListingModel listing = dfe.getSource();
        String localContext = dfe.getLocalContext();
        String id = listing.getId();

        if (Objects.equals(localContext, "listing")) {
            return listing.getAmenities(); // returns List<Amenity>
        }

        DataLoader<String, List<Amenity>> amenityDataLoader = dfe.getDataLoader("amenities");
        return amenityDataLoader.load(id); // returns CompletableFuture<List<Amenity>>
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
