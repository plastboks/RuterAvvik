package net.plastboks.java.rutersugar.domain;

import com.google.gson.annotations.SerializedName;

public class ReisError
{
    @SerializedName("ID")
    private int id;
    @SerializedName("Description")
    private String description;

    public int getId()
    {
        return id;
    }

    public String getDescription()
    {
        return description;
    }
}
