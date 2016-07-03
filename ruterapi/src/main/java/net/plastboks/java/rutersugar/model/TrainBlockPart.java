package net.plastboks.java.rutersugar.model;

import com.google.gson.annotations.SerializedName;

public class TrainBlockPart
{
    @SerializedName("MumberOfBlockParts")
    private int mumberOfBlockParts;

    public int getMumberOfBlockParts()
    {
        return mumberOfBlockParts;
    }
}
