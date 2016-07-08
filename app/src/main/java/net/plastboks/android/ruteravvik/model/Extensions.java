package net.plastboks.android.ruteravvik.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Extensions implements Parcelable
{
    @SerializedName("IsHub")
    private boolean isHub;
    @SerializedName("OccupancyData")
    private OccupancyData occupancyData;
    @SerializedName("Deviations")
    private List<net.plastboks.android.ruteravvik.model.Deviation> deviations;
    @SerializedName("LineColour")
    private String lineColour;

    public boolean isHub()
    {
        return isHub;
    }

    public OccupancyData getOccupancyData()
    {
        return occupancyData;
    }

    public List<net.plastboks.android.ruteravvik.model.Deviation> getDeviations()
    {
        return deviations;
    }

    public String getLineColour()
    {
        return lineColour;
    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeByte(this.isHub ? (byte) 1 : (byte) 0);
        dest.writeParcelable(this.occupancyData, flags);
        dest.writeList(this.deviations);
        dest.writeString(this.lineColour);
    }

    public Extensions()
    {
    }

    protected Extensions(Parcel in)
    {
        this.isHub = in.readByte() != 0;
        this.occupancyData = in.readParcelable(OccupancyData.class.getClassLoader());
        this.deviations = new ArrayList<Deviation>();
        in.readList(this.deviations, Deviation.class.getClassLoader());
        this.lineColour = in.readString();
    }

    public static final Parcelable.Creator<Extensions> CREATOR = new Parcelable.Creator<Extensions>()
    {
        @Override
        public Extensions createFromParcel(Parcel source)
        {
            return new Extensions(source);
        }

        @Override
        public Extensions[] newArray(int size)
        {
            return new Extensions[size];
        }
    };
}
