package net.plastboks.rutersugar.domain;

import com.google.gson.annotations.SerializedName;
import net.plastboks.rutersugar.util.RuterDateParser;

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
    private Extensions extensions;

    public Date getRecordedAtTime()
    {
        return RuterDateParser.toDate(recordedAtTime);
    }

    public String getMonitoringRef()
    {
        return monitoringRef;
    }

    public MonitoredVehicleJourney getMonitoredVehicleJourney()
    {
        return monitoredVehicleJourney;
    }

    public Extensions getExtensions()
    {
        return extensions;
    }

}
