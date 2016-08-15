package net.plastboks.android.ruteravvik.api.service;

import net.plastboks.android.ruteravvik.model.GetAllSalePointsView;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
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
