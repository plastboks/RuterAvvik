package net.plastboks.android.ruteravvik.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import net.plastboks.android.ruteravvik.util.RuterDateParser;

import java.util.Date;

public class MonitoredCall implements Parcelable
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

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeInt(this.visitNumber);
        dest.writeByte(this.vehicleAtStop ? (byte) 1 : (byte) 0);
        dest.writeString(this.destinationDisplay);
        dest.writeString(this.aimedArrivalTime);
        dest.writeString(this.expectedArrivalTime);
        dest.writeString(this.aimedDepartureTime);
        dest.writeString(this.expectedDepartureTime);
        dest.writeString(this.departurePlatformName);
    }

    public MonitoredCall()
    {
    }

    protected MonitoredCall(Parcel in)
    {
        this.visitNumber = in.readInt();
        this.vehicleAtStop = in.readByte() != 0;
        this.destinationDisplay = in.readString();
        this.aimedArrivalTime = in.readString();
        this.expectedArrivalTime = in.readString();
        this.aimedDepartureTime = in.readString();
        this.expectedDepartureTime = in.readString();
        this.departurePlatformName = in.readString();
    }

    public static final Parcelable.Creator<MonitoredCall> CREATOR = new Parcelable.Creator<MonitoredCall>()
    {
        @Override
        public MonitoredCall createFromParcel(Parcel source)
        {
            return new MonitoredCall(source);
        }

        @Override
        public MonitoredCall[] newArray(int size)
        {
            return new MonitoredCall[size];
        }
    };
}
