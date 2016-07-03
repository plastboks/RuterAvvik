package net.plastboks.java.rutersugar.model;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "lines")
public class Line
{
    @DatabaseField(id = true)
    @SerializedName("ID")
    private int id;

    @DatabaseField
    @SerializedName("Name")
    private String name;

    @DatabaseField
    @SerializedName("Transportation")
    private int transportation;

    @DatabaseField
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
