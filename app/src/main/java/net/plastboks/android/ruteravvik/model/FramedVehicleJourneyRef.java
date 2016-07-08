package net.plastboks.android.ruteravvik.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class FramedVehicleJourneyRef implements Parcelable
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

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(this.dataFrameRef);
        dest.writeString(this.datedVehicleJourneyRef);
    }

    public FramedVehicleJourneyRef()
    {
    }

    protected FramedVehicleJourneyRef(Parcel in)
    {
        this.dataFrameRef = in.readString();
        this.datedVehicleJourneyRef = in.readString();
    }

    public static final Parcelable.Creator<FramedVehicleJourneyRef> CREATOR = new Parcelable.Creator<FramedVehicleJourneyRef>()
    {
        @Override
        public FramedVehicleJourneyRef createFromParcel(Parcel source)
        {
            return new FramedVehicleJourneyRef(source);
        }

        @Override
        public FramedVehicleJourneyRef[] newArray(int size)
        {
            return new FramedVehicleJourneyRef[size];
        }
    };
}
