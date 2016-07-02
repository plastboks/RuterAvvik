package net.plastboks.java.rutersugar.service;

import net.plastboks.java.rutersugar.domain.DepartureFavourite;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

public interface DepartureFavouriteService
{
    @GET("favourites/getfavourites")
    Call<List<DepartureFavourite>> getFavorites(@Query("favouritesRequest") String favouritesRequest);
}
