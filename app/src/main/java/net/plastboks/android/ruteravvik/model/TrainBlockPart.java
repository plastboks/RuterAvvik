package net.plastboks.android.ruteravvik.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class TrainBlockPart implements Parcelable
{
    @SerializedName("MumberOfBlockParts")
    private int mumberOfBlockParts;

    public int getMumberOfBlockParts()
    {
        return mumberOfBlockParts;
    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeInt(this.mumberOfBlockParts);
    }

    public TrainBlockPart()
    {
    }

    protected TrainBlockPart(Parcel in)
    {
        this.mumberOfBlockParts = in.readInt();
    }

    public static final Parcelable.Creator<TrainBlockPart> CREATOR = new Parcelable.Creator<TrainBlockPart>()
    {
        @Override
        public TrainBlockPart createFromParcel(Parcel source)
        {
            return new TrainBlockPart(source);
        }

        @Override
        public TrainBlockPart[] newArray(int size)
        {
            return new TrainBlockPart[size];
        }
    };
}
