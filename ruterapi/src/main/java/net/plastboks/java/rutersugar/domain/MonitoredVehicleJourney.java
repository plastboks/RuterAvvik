package net.plastboks.java.rutersugar.domain;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class MonitoredVehicleJourney
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
        return net.plastboks.java.rutersugar.util.RuterDateParser.toDate(originAimedDepartureTime);
    }

    public Date getDestinationAimedArrivalTime()
    {
        return net.plastboks.java.rutersugar.util.RuterDateParser.toDate(destinationAimedArrivalTime);
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
}
