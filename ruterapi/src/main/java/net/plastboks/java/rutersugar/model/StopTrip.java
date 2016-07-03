package net.plastboks.java.rutersugar.model;

import com.google.gson.annotations.SerializedName;

import net.plastboks.java.rutersugar.util.RuterDateParser;

import java.util.Date;
import java.util.List;

public class StopTrip
{
    @SerializedName("ID")
    private int id;
    @SerializedName("District")
    private String district;
    @SerializedName("Name")
    private String name;
    @SerializedName("PlaceType")
    private String placeType;
    @SerializedName("IsHub")
    private boolean isHub;
    @SerializedName("ShortName")
    private String shortName;
    @SerializedName("X")
    private int x;
    @SerializedName("Y")
    private int y;
    @SerializedName("Zone")
    private String zone;
    @SerializedName("AlightingAllowed")
    private boolean alightingAllowed;
    @SerializedName("ArrivalTime")
    private String arrivalTime;
    @SerializedName("BoardingAllowed")
    private boolean boardingAllowed;
    @SerializedName("DepartureTime")
    private String departureTime;
    @SerializedName("Lines")
    private List<Line> lines;

    public int getId()
    {
        return id;
    }

    public String getDistrict()
    {
        return district;
    }

    public String getName()
    {
        return name;
    }

    public String getPlaceType()
    {
        return placeType;
    }

    public boolean isHub()
    {
        return isHub;
    }

    public String getShortName()
    {
        return shortName;
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

    public boolean isAlightingAllowed()
    {
        return alightingAllowed;
    }

    public Date getArrivalTime()
    {
        return RuterDateParser.toDate(arrivalTime);
    }

    public boolean isBoardingAllowed()
    {
        return boardingAllowed;
    }

    public Date getDepartureTime()
    {
        return RuterDateParser.toDate(departureTime);
    }

    public List<Line> getLines()
    {
        return lines;
    }
}
