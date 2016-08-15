package net.plastboks.android.ruteravvik.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class OccupancyData implements Parcelable
{
    @SerializedName("OccupancyAvailable")
    private boolean occupancyAvailable;
    @SerializedName("OccupancyPercentage")
    private int occupancyPercentage;

    public boolean isOccupancyAvailable()
    {
        return occupancyAvailable;
    }

    public int getOccupancyPercentage()
    {
        return occupancyPercentage;
    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeByte(this.occupancyAvailable ? (byte) 1 : (byte) 0);
        dest.writeInt(this.occupancyPercentage);
    }

    public OccupancyData()
    {
    }

    protected OccupancyData(Parcel in)
    {
        this.occupancyAvailable = in.readByte() != 0;
        this.occupancyPercentage = in.readInt();
    }

    public static final Parcelable.Creator<OccupancyData> CREATOR = new Parcelable.Creator<OccupancyData>()
    {
        @Override
        public OccupancyData createFromParcel(Parcel source)
        {
            return new OccupancyData(source);
        }

        @Override
        public OccupancyData[] newArray(int size)
        {
            return new OccupancyData[size];
        }
    };
}
