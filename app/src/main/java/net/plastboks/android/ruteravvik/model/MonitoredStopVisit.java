package net.plastboks.android.ruteravvik.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import net.plastboks.android.ruteravvik.util.RuterDateParser;

import java.util.Date;

public class MonitoredStopVisit implements Parcelable
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

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(this.recordedAtTime);
        dest.writeString(this.monitoringRef);
        dest.writeParcelable(this.monitoredVehicleJourney, flags);
        dest.writeParcelable(this.extensions, flags);
    }

    public MonitoredStopVisit()
    {
    }

    protected MonitoredStopVisit(Parcel in)
    {
        this.recordedAtTime = in.readString();
        this.monitoringRef = in.readString();
        this.monitoredVehicleJourney = in.readParcelable(MonitoredVehicleJourney.class.getClassLoader());
        this.extensions = in.readParcelable(Extensions.class.getClassLoader());
    }

    public static final Parcelable.Creator<MonitoredStopVisit> CREATOR = new Parcelable.Creator<MonitoredStopVisit>()
    {
        @Override
        public MonitoredStopVisit createFromParcel(Parcel source)
        {
            return new MonitoredStopVisit(source);
        }

        @Override
        public MonitoredStopVisit[] newArray(int size)
        {
            return new MonitoredStopVisit[size];
        }
    };
}
