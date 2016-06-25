package net.plastboks.rutersugar.service;

import net.plastboks.rutersugar.domain.MonitoredStopVisit;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

public interface MonitoredStopVisitService
{
    @GET("/StopVisit/GetDepartures/{id}")
    Call<List<MonitoredStopVisit>> getDepartures(@Path("id") int id);

    @GET("/StopVisit/GetDepartures/{id}")
    Call<List<MonitoredStopVisit>> getDepartures(@Path("id") int id,
                                           @Query("datetime") String datetime);
    @GET("/StopVisit/GetDepartures/{id}")
    Call<List<MonitoredStopVisit>> getDepartures(@Path("id") int id,
                                           @Query("datetime") String datetime,
                                           @Query("transporttypes") String transporttypes);
    @GET("/StopVisit/GetDepartures/{id}")
    Call<List<MonitoredStopVisit>> getDepartures(@Path("id") int id,
                                           @Query("datetime") String datetime,
                                           @Query("transporttypes") String transporttypes,
                                           @Query("linenames") String linenames);

}
