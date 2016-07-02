package net.plastboks.java.rutersugar.domain;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class MonitoredStopVisit
{
    @SerializedName("RecordedAtTime")
    private String recordedAtTime;
    @SerializedName("MonitoringRef")
    private String monitoringRef;
    @SerializedName("MonitoredVehicleJourney")
    private MonitoredVehicleJourney monitoredVehicleJourney;
    @SerializedName("Extensions")
    private net.plastboks.java.rutersugar.domain.Extensions extensions;

    public Date getRecordedAtTime()
    {
        return net.plastboks.java.rutersugar.util.RuterDateParser.toDate(recordedAtTime);
    }

    public String getMonitoringRef()
    {
        return monitoringRef;
    }

    public MonitoredVehicleJourney getMonitoredVehicleJourney()
    {
        return monitoredVehicleJourney;
    }

    public net.plastboks.java.rutersugar.domain.Extensions getExtensions()
    {
        return extensions;
    }

}
