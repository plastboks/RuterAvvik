package net.plastboks.java.rutersugar.model;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

@DatabaseTable(tableName = "stops")
public class Stop
{
    @DatabaseField(id = true) @SerializedName("ID")
    private int id;

    @DatabaseField @SerializedName("X")
    private int x;

    @DatabaseField @SerializedName("Y")
    private int y;

    @DatabaseField @SerializedName("Zone")
    private String zone;

    @DatabaseField @SerializedName("ShortName")
    private String shortName;

    @DatabaseField @SerializedName("IsHub")
    private boolean isHub;

    @DatabaseField @SerializedName("Name")
    private String name;

    @DatabaseField @SerializedName("District")
    private String district;

    @DatabaseField @SerializedName("PlaceType")
    private String placeType;

    @DatabaseField @SerializedName("AlightingAllowed")
    private boolean alightingAllowed;

    @DatabaseField @SerializedName("BoardingAllowed")
    private boolean boardingAllowed;

    @DatabaseField @SerializedName("ArrivalTime")
    private String arrivalTime;

    @DatabaseField @SerializedName("DepartureTime")
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
