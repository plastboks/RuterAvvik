package net.plastboks.java.rutersugar.domain;

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
