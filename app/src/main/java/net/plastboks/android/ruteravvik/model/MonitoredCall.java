package net.plastboks.android.ruteravvik.model;

import com.google.gson.annotations.SerializedName;

import net.plastboks.android.ruteravvik.api.util.RuterDateParser;

import java.util.Date;

public class MonitoredCall
{
    @SerializedName("VisitNumber")
    private int visitNumber;
    @SerializedName("VehicleAtStop")
    private boolean vehicleAtStop;
    @SerializedName("DestinationDisplay")
    private String destinationDisplay;
    @SerializedName("AimedArrivalTime")
    private String aimedArrivalTime;
    @SerializedName("ExpectedArrivalTime")
    private String expectedArrivalTime;
    @SerializedName("AimedDepartureTime")
    private String aimedDepartureTime;
    @SerializedName("ExpectedDepartureTime")
    private String expectedDepartureTime;
    @SerializedName("DeparturePlatformName")private String departurePlatformName;

    public int getVisitNumber()
    {
        return visitNumber;
    }

    public boolean isVehicleAtStop()
    {
        return vehicleAtStop;
    }

    public String getDestinationDisplay()
    {
        return destinationDisplay;
    }

    public Date getAimedArrivalTime()
    {
        return RuterDateParser.toDate(aimedArrivalTime);
    }

    public String getAimedArrivalTimeString()
    {
        return aimedArrivalTime;
    }

    public Date getExpectedArrivalTime()
    {
        return RuterDateParser.toDate(expectedArrivalTime);
    }

    public String getExpectedArrivalTimeString()
    {
        return expectedArrivalTime;
    }

    public Date getAimedDepartureTime()
    {
        return RuterDateParser.toDate(aimedDepartureTime);
    }

    public String getAimedDepartureTimeString()
    {
        return aimedDepartureTime;
    }

    public Date getExpectedDepartureTime()
    {
        return RuterDateParser.toDate(expectedDepartureTime);
    }

    public String getExpectedDepartureTimeString()
    {
        return expectedDepartureTime;
    }

    public String getDeparturePlatformName()
    {
        return departurePlatformName;
    }
}
