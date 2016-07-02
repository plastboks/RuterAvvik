package net.plastboks.java.rutersugar.service;

import net.plastboks.java.rutersugar.domain.Valid;

import retrofit.Call;
import retrofit.http.GET;

public interface ValidService
{
    @GET("/meta/getvalidities")
    Call<Valid> getValidities();
}
