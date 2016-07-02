package net.plastboks.java.rutersugar.domain;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Trip
{
    @SerializedName("ID")
    private int id;
    @SerializedName("ReisError")
    private ReisError reisError;
    @SerializedName("Stops")
    private List<StopTrip> stops;

    public int getId()
    {
        return id;
    }

    public ReisError getReisError()
    {
        return reisError;
    }

    public List<StopTrip> getStops()
    {
        return stops;
    }
}
