package net.plastboks.android.ruteravvik.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Street implements Parcelable
{
    @SerializedName("ID")
    private int id;
    @SerializedName("Name")
    private String name;
    @SerializedName("District")
    private String district;
    @SerializedName("PlaceType")
    private String placeType;
    @SerializedName("Houses")
    private List<House> houses;

    public List<House> getHouses()
    {
        return houses;
    }

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
        dest.writeList(this.houses);
    }

    public Street()
    {
    }

    protected Street(Parcel in)
    {
        this.id = in.readInt();
        this.name = in.readString();
        this.district = in.readString();
        this.placeType = in.readString();
        this.houses = new ArrayList<House>();
        in.readList(this.houses, House.class.getClassLoader());
    }

    public static final Parcelable.Creator<Street> CREATOR = new Parcelable.Creator<Street>()
    {
        @Override
        public Street createFromParcel(Parcel source)
        {
            return new Street(source);
        }

        @Override
        public Street[] newArray(int size)
        {
            return new Street[size];
        }
    };
}
