package net.plastboks.java.rutersugar.domain;

import com.google.gson.annotations.SerializedName;

public class OccupancyData
{
    @SerializedName("OccupancyAvailable")
    private boolean occupancyAvailable;
    @SerializedName("OccupancyPercentage")
    private int occupancyPercentage;

    public boolean isOccupancyAvailable()
    {
        return occupancyAvailable;
    }

    public int getOccupancyPercentage()
    {
        return occupancyPercentage;
    }
}
