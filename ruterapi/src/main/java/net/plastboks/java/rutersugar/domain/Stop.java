package net.plastboks.java.rutersugar.domain;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Stop
{
    @SerializedName("ID")
    private int id;
    @SerializedName("X")
    private int x;
    @SerializedName("Y")
    private int y;
    @SerializedName("Zone")
    private String zone;
    @SerializedName("ShortName")
    private String shortName;
    @SerializedName("IsHub")
    private boolean isHub;
    @SerializedName("Name")
    private String name;
    @SerializedName("District")
    private String district;
    @SerializedName("PlaceType")
    private String placeType;
    @SerializedName("AlightingAllowed")
    private boolean alightingAllowed;
    @SerializedName("BoardingAllowed")
    private boolean boardingAllowed;
    @SerializedName("ArrivalTime")
    private String arrivalTime;
    @SerializedName("DepartureTime")
    private String departureTime;

    public int getId()
    {
        return id;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public String getZone()
    {
        return zone;
    }

    public String getShortName()
    {
        return shortName;
    }

    public boolean isHub()
    {
        return isHub;
    }

    public String getName()
    {
        return name;
    }

    public String getDistrict()
    {
        return district;
    }

    public String getPlaceType()
    {
        return placeType;
    }

    public boolean isAlightingAllowed()
    {
        return alightingAllowed;
    }

    public boolean isBoardingAllowed()
    {
        return boardingAllowed;
    }

    public Date getArrivalTime()
    {
        return net.plastboks.java.rutersugar.util.RuterDateParser.toDate(arrivalTime);
    }

    public Date getDepartureTime()
    {
        return net.plastboks.java.rutersugar.util.RuterDateParser.toDate(departureTime);
    }

    public String toString()
    {
        return String.format("ID: %d, Name: %s",
                id, name);
    }
}
