package net.plastboks.android.ruteravvik.api.service;

import net.plastboks.android.ruteravvik.model.Street;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface StreetService
{
    @GET("/street/getstreet/{id}")
    Call<Street> getStreet(@Path("id") int id);
    @GET("/street/getstreet/{id}")
    Observable<Street> getStreetRx(@Path("id") int id);
}
