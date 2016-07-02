package net.plastboks.java.rutersugar.domain;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DepartureFavourite
{
    @SerializedName("StopID")
    private int stopID;
    @SerializedName("LineID")
    private int lineID;
    @SerializedName("Destination")
    private String destination;
    @SerializedName("MonitoredStopVisits")
    private List<MonitoredStopVisit> monitoredStopVisits;

    public int getStopID()
    {
        return stopID;
    }

    public int getLineID()
    {
        return lineID;
    }

    public String getDestination()
    {
        return destination;
    }

    public List<MonitoredStopVisit> getMonitoredStopVisits()
    {
        return monitoredStopVisits;
    }
}
