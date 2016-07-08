package net.plastboks.android.ruteravvik.model;

import com.google.gson.annotations.SerializedName;

public class Valid
{
    @SerializedName("ValidFrom") private String validFrom;
    @SerializedName("ValidTo") private String validTo;

    public String getValidFrom()
    {
        return validFrom;
    }

    public String getValidTo()
    {
        return validTo;
    }
}