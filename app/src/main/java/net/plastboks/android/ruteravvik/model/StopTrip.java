package net.plastboks.android.ruteravvik.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import net.plastboks.android.ruteravvik.util.RuterDateParser;

import java.util.Date;
import java.util.List;

public class StopTrip implements Parcelable
{
    @SerializedName("ID")
    private int id;
    @SerializedName("District")
    private String district;
    @SerializedName("Name")
    private String name;
    @SerializedName("PlaceType")
    private String placeType;
    @SerializedName("IsHub")
    private boolean isHub;
    @SerializedName("ShortName")
    private String shortName;
    @SerializedName("X")
    private int x;
    @SerializedName("Y")
    private int y;
    @SerializedName("Zone")
    private String zone;
    @SerializedName("AlightingAllowed")
    private boolean alightingAllowed;
    @SerializedName("ArrivalTime")
    private String arrivalTime;
    @SerializedName("BoardingAllowed")
    private boolean boardingAllowed;
    @SerializedName("DepartureTime")
    private String departureTime;
    @SerializedName("Lines")
    private List<Line> lines;

    public int getId()
    {
        return id;
    }

    public String getDistrict()
    {
        return district;
    }

    public String getName()
    {
        return name;
    }

    public String getPlaceType()
    {
        return placeType;
    }

    public boolean isHub()
    {
        return isHub;
    }

    public String getShortName()
    {
        return shortName;
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

    public boolean isAlightingAllowed()
    {
        return alightingAllowed;
    }

    public Date getArrivalTime()
    {
        return RuterDateParser.toDate(arrivalTime);
    }

    public boolean isBoardingAllowed()
    {
        return boardingAllowed;
    }

    public Date getDepartureTime()
    {
        return RuterDateParser.toDate(departureTime);
    }

    public List<Line> getLines()
    {
        return lines;
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
        dest.writeString(this.district);
        dest.writeString(this.name);
        dest.writeString(this.placeType);
        dest.writeByte(this.isHub ? (byte) 1 : (byte) 0);
        dest.writeString(this.shortName);
        dest.writeInt(this.x);
        dest.writeInt(this.y);
        dest.writeString(this.zone);
        dest.writeByte(this.alightingAllowed ? (byte) 1 : (byte) 0);
        dest.writeString(this.arrivalTime);
        dest.writeByte(this.boardingAllowed ? (byte) 1 : (byte) 0);
        dest.writeString(this.departureTime);
        dest.writeTypedList(this.lines);
    }

    public StopTrip()
    {
    }

    protected StopTrip(Parcel in)
    {
        this.id = in.readInt();
        this.district = in.readString();
        this.name = in.readString();
        this.placeType = in.readString();
        this.isHub = in.readByte() != 0;
        this.shortName = in.readString();
        this.x = in.readInt();
        this.y = in.readInt();
        this.zone = in.readString();
        this.alightingAllowed = in.readByte() != 0;
        this.arrivalTime = in.readString();
        this.boardingAllowed = in.readByte() != 0;
        this.departureTime = in.readString();
        this.lines = in.createTypedArrayList(Line.CREATOR);
    }

    public static final Parcelable.Creator<StopTrip> CREATOR = new Parcelable.Creator<StopTrip>()
    {
        @Override
        public StopTrip createFromParcel(Parcel source)
        {
            return new StopTrip(source);
        }

        @Override
        public StopTrip[] newArray(int size)
        {
            return new StopTrip[size];
        }
    };
}
