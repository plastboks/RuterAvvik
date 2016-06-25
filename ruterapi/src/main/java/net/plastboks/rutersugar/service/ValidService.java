package net.plastboks.rutersugar.service;

import net.plastboks.rutersugar.domain.Valid;

import retrofit.Call;
import retrofit.http.GET;

public interface ValidService
{
    @GET("/meta/getvalidities")
    Call<Valid> getValidities();
}
