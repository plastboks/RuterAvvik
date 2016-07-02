package net.plastboks.java.rutersugar.domain;

import com.google.gson.annotations.SerializedName;

public class Line
{
    @SerializedName("ID")
    private int id;
    @SerializedName("Name")
    private String name;
    @SerializedName("Transportation")
    private int transportation;
    @SerializedName("LineColour")
    private String lineColour;

    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public int getTransportation()
    {
        return transportation;
    }

    public String getLineColour()
    {
        return lineColour;
    }

    public String toString()
    {
        return String.format("ID: %d, Name: %s, Transportation %d, Linecolor: %s",
                id, name.trim(), transportation, lineColour.trim());
    }

}
