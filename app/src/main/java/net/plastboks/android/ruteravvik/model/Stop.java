package net.plastboks.android.ruteravvik.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import net.plastboks.android.ruteravvik.api.util.RuterDateParser;

import java.util.Date;

@DatabaseTable(tableName = "stops")
public class Stop implements Parcelable
{
    @DatabaseField(id = true) @SerializedName("ID")
    private int id;

    @DatabaseField @SerializedName("X")
    private int x;

    @DatabaseField @SerializedName("Y")
    private int y;

    @DatabaseField @SerializedName("Zone")
    private String zone;

    @DatabaseField @SerializedName("ShortName")
    private String shortName;

    @DatabaseField @SerializedName("IsHub")
    private boolean isHub;

    @DatabaseField @SerializedName("Name")
    private String name;

    @DatabaseField @SerializedName("District")
    private String district;

    @DatabaseField @SerializedName("PlaceType")
    private String placeType;

    @DatabaseField @SerializedName("AlightingAllowed")
    private boolean alightingAllowed;

    @DatabaseField @SerializedName("BoardingAllowed")
    private boolean boardingAllowed;

    @DatabaseField @SerializedName("ArrivalTime")
    private String arrivalTime;

    @DatabaseField @SerializedName("DepartureTime")
    private String departureTime;

    public int getId()
    {
        return id;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public String getZone()
    {
        return zone;
    }

    public String getShortName()
    {
        return shortName;
    }

    public boolean isHub()
    {
        return isHub;
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

    public boolean isAlightingAllowed()
    {
        return alightingAllowed;
    }

    public boolean isBoardingAllowed()
    {
        return boardingAllowed;
    }

    public Date getArrivalTime()
    {
        return RuterDateParser.toDate(arrivalTime);
    }

    public Date getDepartureTime()
    {
        return RuterDateParser.toDate(departureTime);
    }

    public String toString()
    {
        return String.format("ID: %d, Name: %s",
                id, name);
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
        dest.writeInt(this.x);
        dest.writeInt(this.y);
        dest.writeString(this.zone);
        dest.writeString(this.shortName);
        dest.writeByte(this.isHub ? (byte) 1 : (byte) 0);
        dest.writeString(this.name);
        dest.writeString(this.district);
        dest.writeString(this.placeType);
        dest.writeByte(this.alightingAllowed ? (byte) 1 : (byte) 0);
        dest.writeByte(this.boardingAllowed ? (byte) 1 : (byte) 0);
        dest.writeString(this.arrivalTime);
        dest.writeString(this.departureTime);
    }

    public Stop()
    {
    }

    protected Stop(Parcel in)
    {
        this.id = in.readInt();
        this.x = in.readInt();
        this.y = in.readInt();
        this.zone = in.readString();
        this.shortName = in.readString();
        this.isHub = in.readByte() != 0;
        this.name = in.readString();
        this.district = in.readString();
        this.placeType = in.readString();
        this.alightingAllowed = in.readByte() != 0;
        this.boardingAllowed = in.readByte() != 0;
        this.arrivalTime = in.readString();
        this.departureTime = in.readString();
    }

    public static final Parcelable.Creator<Stop> CREATOR = new Parcelable.Creator<Stop>()
    {
        @Override
        public Stop createFromParcel(Parcel source)
        {
            return new Stop(source);
        }

        @Override
        public Stop[] newArray(int size)
        {
            return new Stop[size];
        }
    };
}
