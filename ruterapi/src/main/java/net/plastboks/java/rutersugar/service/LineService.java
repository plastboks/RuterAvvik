package net.plastboks.java.rutersugar.service;

import net.plastboks.java.rutersugar.domain.Line;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;

public interface LineService
{
    @GET("/line/getlines")
    Call<List<Line>> getLines();

    @GET("/line/getlinesruter")
    Call<List<Line>> getLinesRuter();

    @GET("/line/getlinesbystopid/{id}")
    Call<List<Line>> getLinesByStopID(@Path("id") int id);

    @GET("/line/getdatabylineid/{id}")
    Call<Line> getDataByLineID(@Path("id") int id);

}
