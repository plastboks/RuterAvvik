package net.plastboks.android.ruteravvik.api.service;

import net.plastboks.android.ruteravvik.model.MonitoredStopVisit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface MonitoredStopVisitService
{
    @GET("/StopVisit/GetDepartures/{id}")
    Call<List<MonitoredStopVisit>> getDepartures(@Path("id") int id);
    @GET("/StopVisit/GetDepartures/{id}")
    Observable<List<MonitoredStopVisit>> getDeparturesRx(@Path("id") int id);

    @GET("/StopVisit/GetDepartures/{id}")
    Call<List<MonitoredStopVisit>> getDepartures(@Path("id") int id,
                                           @Query("datetime") String datetime);
    @GET("/StopVisit/GetDepartures/{id}")
    Observable<List<MonitoredStopVisit>> getDeparturesRx(@Path("id") int id,
                                                 @Query("datetime") String datetime);

    @GET("/StopVisit/GetDepartures/{id}")
    Call<List<MonitoredStopVisit>> getDepartures(@Path("id") int id,
                                           @Query("datetime") String datetime,
                                           @Query("transporttypes") String transporttypes);
    @GET("/StopVisit/GetDepartures/{id}")
    Observable<List<MonitoredStopVisit>> getDeparturesRx(@Path("id") int id,
                                                 @Query("datetime") String datetime,
                                                 @Query("transporttypes") String transporttypes);

    @GET("/StopVisit/GetDepartures/{id}")
    Call<List<MonitoredStopVisit>> getDepartures(@Path("id") int id,
                                           @Query("datetime") String datetime,
                                           @Query("transporttypes") String transporttypes,
                                           @Query("linenames") String linenames);
    @GET("/StopVisit/GetDepartures/{id}")
    Observable<List<MonitoredStopVisit>> getDeparturesRx(@Path("id") int id,
                                                 @Query("datetime") String datetime,
                                                 @Query("transporttypes") String transporttypes,
                                                 @Query("linenames") String linenames);

}
