package net.plastboks.java.rutersugar.model;

import com.google.gson.annotations.SerializedName;

public class Place
{
    @SerializedName("ID")
    private int id;
    @SerializedName("Name")
    private String name;
    @SerializedName("District")
    private String district;
    @SerializedName("PlaceType")
    private String placeType;

    public int getId()
    {
        return id;
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

    @Override
    public String toString()
    {
        return String.format("ID: %d, Name: %s, District: %s, PlaceType: %s",
                id, name, district, placeType);
    }
}
