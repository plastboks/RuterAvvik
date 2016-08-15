package net.plastboks.android.ruteravvik.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Place implements Parcelable
{
    @SerializedName("ID")
    private int id;
    @SerializedName("Name")
    private String name;
    @SerializedName("District")
    private String district;
    @SerializedName("PlaceType")
    private String placeType;

    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public String getDistrict()
    {
        return district;
    }

    public String getPlaceType()
    {
        return placeType;
    }

    @Override
    public String toString()
    {
        return String.format("ID: %d, Name: %s, District: %s, PlaceType: %s",
                id, name, district, placeType);
    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.district);
        dest.writeString(this.placeType);
    }

    public Place()
    {
    }

    protected Place(Parcel in)
    {
        this.id = in.readInt();
        this.name = in.readString();
        this.district = in.readString();
        this.placeType = in.readString();
    }

    public static final Parcelable.Creator<Place> CREATOR = new Parcelable.Creator<Place>()
    {
        @Override
        public Place createFromParcel(Parcel source)
        {
            return new Place(source);
        }

        @Override
        public Place[] newArray(int size)
        {
            return new Place[size];
        }
    };
}
