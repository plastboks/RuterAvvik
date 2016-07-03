package net.plastboks.java.rutersugar.model;

import com.google.gson.annotations.SerializedName;

public class FramedVehicleJourneyRef
{
    @SerializedName("DataFrameRef")
    private String dataFrameRef;
    @SerializedName("DatedVehicleJourneyRef")
    private String datedVehicleJourneyRef;

    public String getDataFrameRef()
    {
        return dataFrameRef;
    }

    public String getDatedVehicleJourneyRef()
    {
        return datedVehicleJourneyRef;
    }
}
