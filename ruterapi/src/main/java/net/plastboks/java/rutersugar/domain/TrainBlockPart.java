package net.plastboks.java.rutersugar.domain;

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
