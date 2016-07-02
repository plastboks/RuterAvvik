package net.plastboks.java.rutersugar.domain;

import com.google.gson.annotations.SerializedName;

public class Deviation
{
    @SerializedName("ID")
    private int id;
    @SerializedName("Header")
    private String header;

    public int getId()
    {
        return id;
    }

    public String getHeader()
    {
        return header;
    }
}
