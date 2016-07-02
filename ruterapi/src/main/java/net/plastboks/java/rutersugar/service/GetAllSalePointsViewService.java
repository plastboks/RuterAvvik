package net.plastboks.java.rutersugar.service;

import net.plastboks.java.rutersugar.domain.GetAllSalePointsView;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

public interface GetAllSalePointsViewService
{
    @GET("/Place/GetSalePointsByArea")
    Call<List<GetAllSalePointsView>> getSalePointsByArea(@Query("longmin") String longmin,
                                                         @Query("longmax") String longmax,
                                                         @Query("latmin") String latmin,
                                                         @Query("latmax") String latmax);

}
