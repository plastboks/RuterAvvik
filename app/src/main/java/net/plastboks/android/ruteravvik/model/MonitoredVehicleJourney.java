package net.plastboks.android.ruteravvik.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import net.plastboks.android.ruteravvik.api.util.RuterDateParser;

import java.util.Date;

public class MonitoredVehicleJourney implements Parcelable
{
    @SerializedName("LineRef")
    private String lineRef;
    @SerializedName("DirectionRef")
    private String directionRef;
    @SerializedName("FramedVehicleJourneyRef")
    private FramedVehicleJourneyRef framedVehicleJourneyRef;
    @SerializedName("PublishedLineName")
    private String publishedLineName;
    @SerializedName("DirectionName")
    private String directionName;
    @SerializedName("OperatorRef")
    private String operatorRef;
    @SerializedName("OriginName")
    private String originName;
    @SerializedName("OriginRef")
    private String originRef;
    @SerializedName("DestinationRef")
    private int destinationRef;
    @SerializedName("DestinationName")
    private String destinationName;
    @SerializedName("OriginAimedDepartureTime")
    private String originAimedDepartureTime;
    @SerializedName("DestinationAimedArrivalTime")
    private String destinationAimedArrivalTime;
    @SerializedName("Monitored")
    private boolean monitored;
    @SerializedName("InCongestion")
    private boolean inCongestion;
    @SerializedName("Delay")
    private String delay;
    @SerializedName("TrainBlockPart")
    private TrainBlockPart trainBlockPart;
    @SerializedName("BlockRef")
    private String blockRef;
    @SerializedName("VehicleRef")
    private String vehicleRef;
    @SerializedName("VehicleMode")
    private int vehicleMode;
    @SerializedName("VehicleJourneyName")
    private String vehicleJourneyName;
    @SerializedName("MonitoredCall")
    private MonitoredCall monitoredCall;
    @SerializedName("VehicleFeatureRef")
    private String vehicleFeatureRef;

    public String getLineRef()
    {
        return lineRef;
    }

    public String getDirectionRef()
    {
        return directionRef;
    }

    public FramedVehicleJourneyRef getFramedVehicleJourneyRef()
    {
        return framedVehicleJourneyRef;
    }

    public String getPublishedLineName()
    {
        return publishedLineName;
    }

    public String getDirectionName()
    {
        return directionName;
    }

    public String getOperatorRef()
    {
        return operatorRef;
    }

    public String getOriginName()
    {
        return originName;
    }

    public String getOriginRef()
    {
        return originRef;
    }

    public int getDestinationRef()
    {
        return destinationRef;
    }

    public String getDestinationName()
    {
        return destinationName;
    }

    public Date getOriginAimedDepartureTime()
    {
        return RuterDateParser.toDate(originAimedDepartureTime);
    }

    public Date getDestinationAimedArrivalTime()
    {
        return RuterDateParser.toDate(destinationAimedArrivalTime);
    }

    public boolean isMonitored()
    {
        return monitored;
    }

    public boolean isInCongestion()
    {
        return inCongestion;
    }

    public String getDelay()
    {
        return delay;
    }

    public TrainBlockPart getTrainBlockPart()
    {
        return trainBlockPart;
    }

    public String getBlockRef()
    {
        return blockRef;
    }

    public String getVehicleRef()
    {
        return vehicleRef;
    }

    public int getVehicleMode()
    {
        return vehicleMode;
    }

    public String getVehicleJourneyName()
    {
        return vehicleJourneyName;
    }

    public MonitoredCall getMonitoredCall()
    {
        return monitoredCall;
    }

    public String getVehicleFeatureRef()
    {
        return vehicleFeatureRef;
    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(this.lineRef);
        dest.writeString(this.directionRef);
        dest.writeParcelable(this.framedVehicleJourneyRef, flags);
        dest.writeString(this.publishedLineName);
        dest.writeString(this.directionName);
        dest.writeString(this.operatorRef);
        dest.writeString(this.originName);
        dest.writeString(this.originRef);
        dest.writeInt(this.destinationRef);
        dest.writeString(this.destinationName);
        dest.writeString(this.originAimedDepartureTime);
        dest.writeString(this.destinationAimedArrivalTime);
        dest.writeByte(this.monitored ? (byte) 1 : (byte) 0);
        dest.writeByte(this.inCongestion ? (byte) 1 : (byte) 0);
        dest.writeString(this.delay);
        dest.writeParcelable(this.trainBlockPart, flags);
        dest.writeString(this.blockRef);
        dest.writeString(this.vehicleRef);
        dest.writeInt(this.vehicleMode);
        dest.writeString(this.vehicleJourneyName);
        dest.writeParcelable(this.monitoredCall, flags);
        dest.writeString(this.vehicleFeatureRef);
    }

    public MonitoredVehicleJourney()
    {
    }

    protected MonitoredVehicleJourney(Parcel in)
    {
        this.lineRef = in.readString();
        this.directionRef = in.readString();
        this.framedVehicleJourneyRef = in.readParcelable(FramedVehicleJourneyRef.class.getClassLoader());
        this.publishedLineName = in.readString();
        this.directionName = in.readString();
        this.operatorRef = in.readString();
        this.originName = in.readString();
        this.originRef = in.readString();
        this.destinationRef = in.readInt();
        this.destinationName = in.readString();
        this.originAimedDepartureTime = in.readString();
        this.destinationAimedArrivalTime = in.readString();
        this.monitored = in.readByte() != 0;
        this.inCongestion = in.readByte() != 0;
        this.delay = in.readString();
        this.trainBlockPart = in.readParcelable(TrainBlockPart.class.getClassLoader());
        this.blockRef = in.readString();
        this.vehicleRef = in.readString();
        this.vehicleMode = in.readInt();
        this.vehicleJourneyName = in.readString();
        this.monitoredCall = in.readParcelable(MonitoredCall.class.getClassLoader());
        this.vehicleFeatureRef = in.readString();
    }

    public static final Parcelable.Creator<MonitoredVehicleJourney> CREATOR = new Parcelable.Creator<MonitoredVehicleJourney>()
    {
        @Override
        public MonitoredVehicleJourney createFromParcel(Parcel source)
        {
            return new MonitoredVehicleJourney(source);
        }

        @Override
        public MonitoredVehicleJourney[] newArray(int size)
        {
            return new MonitoredVehicleJourney[size];
        }
    };
}
