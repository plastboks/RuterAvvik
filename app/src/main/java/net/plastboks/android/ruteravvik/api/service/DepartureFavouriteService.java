package net.plastboks.android.ruteravvik.api.service;

import net.plastboks.android.ruteravvik.model.DepartureFavourite;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface DepartureFavouriteService
{
    @GET("favourites/getfavourites")
    Call<List<DepartureFavourite>> getFavorites(@Query("favouritesRequest") String favouritesRequest);

    @GET("favourites/getfavourites")
    Observable<List<DepartureFavourite>> getFavoritesRx(@Query("favouritesRequest") String favouritesRequest);
}
