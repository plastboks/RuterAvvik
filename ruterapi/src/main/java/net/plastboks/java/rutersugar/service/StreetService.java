package net.plastboks.java.rutersugar.service;

import net.plastboks.java.rutersugar.domain.Street;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;

public interface StreetService
{
    @GET("/street/getstreet/{id}")
    Call<Street> getStreet(@Path("id") int id);
}
