package net.plastboks.rutersugar.service;

import net.plastboks.rutersugar.domain.Stop;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;

public interface StopService
{
    @GET("/Place/GetStop/{id}")
    Call<Stop> getStop(@Path("id") int id);

    @GET("/Place/GetArea/{id}")
    Call<Stop> getArea(@Path("id") int id);

    @GET("/Place/GetStopsRuter")
    Call<List<Stop>> getStopsRuter();

    @GET("/line/getstopsbylineid/{id}")
    Call<List<Stop>> getStopsByLineID(@Path("id") int id);
}
