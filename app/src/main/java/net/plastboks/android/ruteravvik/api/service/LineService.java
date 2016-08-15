package net.plastboks.android.ruteravvik.api.service;


import net.plastboks.android.ruteravvik.api.DateList;
import net.plastboks.android.ruteravvik.model.Line;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface LineService
{
    @GET("/line/getlines")
    Call<DateList<Line>> getLines();
    @GET("/line/getlines")
    Observable<DateList<Line>> getLinesRx();

    @GET("/line/getlinesruter")
    Call<DateList<Line>> getLinesRuter();
    @GET("/line/getlinesruter")
    Observable<DateList<Line>> getLinesRuterRx();

    @GET("/line/getlinesbystopid/{id}")
    Call<DateList<Line>> getLinesByStopID(@Path("id") int id);
    @GET("/line/getlinesbystopid/{id}")
    Observable<DateList<Line>> getLinesByStopIDRx(@Path("id") int id);

    @GET("/line/getdatabylineid/{id}")
    Call<Line> getDataByLineID(@Path("id") int id);
    @GET("/line/getdatabylineid/{id}")
    Observable<Line> getDataByLineIDRx(@Path("id") int id);

}
