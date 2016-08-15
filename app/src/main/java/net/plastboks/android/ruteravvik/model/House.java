package net.plastboks.android.ruteravvik.model;

import com.google.gson.annotations.SerializedName;

public class House
{
    @SerializedName("Name")
    private String name;
    @SerializedName("X")
    private int x;
    @SerializedName("Y")
    private int y;

    public String getName()
    {
        return name;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }
}
