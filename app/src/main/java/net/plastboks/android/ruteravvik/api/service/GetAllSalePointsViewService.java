package net.plastboks.android.ruteravvik.api.service;

import net.plastboks.android.ruteravvik.model.GetAllSalePointsView;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

public interface GetAllSalePointsViewService
{
    @GET("/Place/GetSalePointsByArea")
    Call<List<GetAllSalePointsView>> getSalePointsByArea(@Query("longmin") String longmin,
                                                         @Query("longmax") String longmax,
                                                         @Query("latmin") String latmin,
                                                         @Query("latmax") String latmax);

    @GET("/Place/GetSalePointsByArea")
    Observable<List<GetAllSalePointsView>> getSalePointsByAreaRx(@Query("longmin") String longmin,
                                                               @Query("longmax") String longmax,
                                                               @Query("latmin") String latmin,
                                                               @Query("latmax") String latmax);
}