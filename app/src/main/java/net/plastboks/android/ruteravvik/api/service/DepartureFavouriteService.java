package net.plastboks.android.ruteravvik.api.service;

import net.plastboks.android.ruteravvik.model.DepartureFavourite;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

public interface DepartureFavouriteService
{
    @GET("favourites/getfavourites")
    Call<List<DepartureFavourite>> getFavorites(@Query("favouritesRequest") String favouritesRequest);

    @GET("favourites/getfavourites")
    Observable<List<DepartureFavourite>> getFavoritesRx(@Query("favouritesRequest") String favouritesRequest);
}
