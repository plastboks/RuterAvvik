package net.plastboks.java.rutersugar.service;

import net.plastboks.java.rutersugar.domain.Valid;

import retrofit.Call;
import retrofit.http.GET;
import rx.Observable;

public interface ValidService
{
    @GET("/meta/getvalidities")
    Call<Valid> getValidities();
    @GET("/meta/getvalidities")
    Observable<Valid> getValiditiesRx();
}
