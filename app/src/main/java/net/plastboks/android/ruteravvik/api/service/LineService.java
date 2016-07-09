package net.plastboks.android.ruteravvik.api.service;


import net.plastboks.android.ruteravvik.model.Line;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface LineService
{
    @GET("/line/getlines")
    Call<List<Line>> getLines();
    @GET("/line/getlines")
    Observable<List<Line>> getLinesRx();

    @GET("/line/getlinesruter")
    Call<List<Line>> getLinesRuter();
    @GET("/line/getlinesruter")
    Observable<List<Line>> getLinesRuterRx();

    @GET("/line/getlinesbystopid/{id}")
    Call<List<Line>> getLinesByStopID(@Path("id") int id);
    @GET("/line/getlinesbystopid/{id}")
    Observable<List<Line>> getLinesByStopIDRx(@Path("id") int id);

    @GET("/line/getdatabylineid/{id}")
    Call<Line> getDataByLineID(@Path("id") int id);
    @GET("/line/getdatabylineid/{id}")
    Observable<Line> getDataByLineIDRx(@Path("id") int id);

}
