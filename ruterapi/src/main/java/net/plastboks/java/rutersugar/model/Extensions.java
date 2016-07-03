package net.plastboks.java.rutersugar.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Extensions
{
    @SerializedName("IsHub")
    private boolean isHub;
    @SerializedName("OccupancyData")
    private OccupancyData occupancyData;
    @SerializedName("Deviations")
    private List<Deviation> deviations;
    @SerializedName("LineColour")
    private String lineColour;

    public boolean isHub()
    {
        return isHub;
    }

    public OccupancyData getOccupancyData()
    {
        return occupancyData;
    }

    public List<Deviation> getDeviations()
    {
        return deviations;
    }

    public String getLineColour()
    {
        return lineColour;
    }

}
