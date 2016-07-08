package net.plastboks.android.ruteravvik.api.service;


import net.plastboks.android.ruteravvik.model.Valid;

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
