package net.plastboks.android.ruteravvik.api.service;

import net.plastboks.android.ruteravvik.model.Street;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

public interface StreetService
{
    @GET("/street/getstreet/{id}")
    Call<Street> getStreet(@Path("id") int id);
    @GET("/street/getstreet/{id}")
    Observable<Street> getStreetRx(@Path("id") int id);
}
