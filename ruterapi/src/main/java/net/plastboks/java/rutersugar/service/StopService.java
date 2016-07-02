package net.plastboks.java.rutersugar.service;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;

public interface StopService
{
    @GET("/Place/GetStop/{id}")
    Call<net.plastboks.java.rutersugar.domain.Stop> getStop(@Path("id") int id);

    @GET("/Place/GetArea/{id}")
    Call<net.plastboks.java.rutersugar.domain.Stop> getArea(@Path("id") int id);

    @GET("/Place/GetStopsRuter")
    Call<List<net.plastboks.java.rutersugar.domain.Stop>> getStopsRuter();

    @GET("/line/getstopsbylineid/{id}")
    Call<List<net.plastboks.java.rutersugar.domain.Stop>> getStopsByLineID(@Path("id") int id);
}
