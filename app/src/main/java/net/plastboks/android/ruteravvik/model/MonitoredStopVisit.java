package net.plastboks.android.ruteravvik.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import net.plastboks.android.ruteravvik.api.util.RuterDateParser;

import java.util.Date;

@DatabaseTable(tableName = "stopvisits")
public class MonitoredStopVisit implements Parcelable
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
