package net.plastboks.android.ruteravvik.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DepartureFavourite implements Parcelable
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

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeInt(this.stopID);
        dest.writeInt(this.lineID);
        dest.writeString(this.destination);
        dest.writeTypedList(this.monitoredStopVisits);
    }

    public DepartureFavourite()
    {
    }

    protected DepartureFavourite(Parcel in)
    {
        this.stopID = in.readInt();
        this.lineID = in.readInt();
        this.destination = in.readString();
        this.monitoredStopVisits = in.createTypedArrayList(MonitoredStopVisit.CREATOR);
    }

    public static final Parcelable.Creator<DepartureFavourite> CREATOR = new Parcelable.Creator<DepartureFavourite>()
    {
        @Override
        public DepartureFavourite createFromParcel(Parcel source)
        {
            return new DepartureFavourite(source);
        }

        @Override
        public DepartureFavourite[] newArray(int size)
        {
            return new DepartureFavourite[size];
        }
    };
}
