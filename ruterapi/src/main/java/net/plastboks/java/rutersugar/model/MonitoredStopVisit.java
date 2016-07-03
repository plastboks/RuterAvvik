package net.plastboks.java.rutersugar.model;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

@DatabaseTable(tableName = "stopvisits")
public class MonitoredStopVisit
{
    @DatabaseField(id = true)
    @SerializedName("RecordedAtTime")
    private String recordedAtTime;

    @DatabaseField
    @SerializedName("MonitoringRef")
    private String monitoringRef;

    @SerializedName("MonitoredVehicleJourney")
    private MonitoredVehicleJourney monitoredVehicleJourney;

    @SerializedName("Extensions")
    private Extensions extensions;

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

    public Extensions getExtensions()
    {
        return extensions;
    }

}
