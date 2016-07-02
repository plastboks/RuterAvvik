package net.plastboks.java.rutersugar.domain;

import com.google.gson.annotations.SerializedName;

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
        return net.plastboks.java.rutersugar.util.RuterDateParser.toDate(aimedArrivalTime);
    }

    public String getAimedArrivalTimeString()
    {
        return aimedArrivalTime;
    }

    public Date getExpectedArrivalTime()
    {
        return net.plastboks.java.rutersugar.util.RuterDateParser.toDate(expectedArrivalTime);
    }

    public String getExpectedArrivalTimeString()
    {
        return expectedArrivalTime;
    }

    public Date getAimedDepartureTime()
    {
        return net.plastboks.java.rutersugar.util.RuterDateParser.toDate(aimedDepartureTime);
    }

    public String getAimedDepartureTimeString()
    {
        return aimedDepartureTime;
    }

    public Date getExpectedDepartureTime()
    {
        return net.plastboks.java.rutersugar.util.RuterDateParser.toDate(expectedDepartureTime);
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
