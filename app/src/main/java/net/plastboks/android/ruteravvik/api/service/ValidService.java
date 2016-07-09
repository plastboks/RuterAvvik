package net.plastboks.android.ruteravvik.api.service;


import net.plastboks.android.ruteravvik.model.Valid;

import retrofit2.Call;
import retrofit2.http.GET;
import rx.Observable;

public interface ValidService
{
    @GET("/meta/getvalidities")
    Call<Valid> getValidities();
    @GET("/meta/getvalidities")
    Observable<Valid> getValiditiesRx();
}
