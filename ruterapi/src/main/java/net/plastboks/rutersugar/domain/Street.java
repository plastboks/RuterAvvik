package net.plastboks.rutersugar.domain;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Street
{
    @SerializedName("ID")
    private int id;
    @SerializedName("Name")
    private String name;
    @SerializedName("District")
    private String district;
    @SerializedName("PlaceType")
    private String placeType;
    @SerializedName("Houses")
    private List<House> houses;

    public List<House> getHouses()
    {
        return houses;
    }

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

    public String toString()
    {
        return String.format("ID: %d, Name: %s, District: %s, PlaceType: %s",
                id, name, district, placeType);
    }
}
