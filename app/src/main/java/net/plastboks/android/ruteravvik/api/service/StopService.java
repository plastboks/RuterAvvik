package net.plastboks.android.ruteravvik.api.service;

import net.plastboks.android.ruteravvik.model.Stop;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface StopService
{
    @GET("/Place/GetStop/{id}")
    Call<Stop> getStop(@Path("id") int id);
    @GET("/Place/GetStop/{id}")
    Observable<Stop> getStopRx(@Path("id") int id);

    @GET("/Place/GetArea/{id}")
    Call<Stop> getArea(@Path("id") int id);
    @GET("/Place/GetArea/{id}")
    Observable<Stop> getAreaRx(@Path("id") int id);

    @GET("/Place/GetStopsRuter")
    Call<List<Stop>> getStopsRuter();
    @GET("/Place/GetStopsRuter")
    Observable<List<Stop>> getStopsRuterRx();

    @GET("/line/getstopsbylineid/{id}")
    Call<List<Stop>> getStopsByLineId(@Path("id") int id);
    @GET("/line/getstopsbylineid/{id}")
    Observable<List<Stop>> getStopsByLineIdRx(@Path("id") int id);
}
