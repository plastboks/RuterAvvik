package net.plastboks.android.ruteravvik.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import net.plastboks.android.ruteravvik.api.contract.ExpirationDate;
import net.plastboks.android.ruteravvik.util.RuterDateParser;

import org.joda.time.DateTime;

import java.util.Date;

public class Stop implements Parcelable, ExpirationDate
{
    public final static String FIELD_FAVORITE = "favorite";
    public final static String FIELD_UNWANTED = "unwanted";

    public static final Long expiration = 1000*1000L;

    private int id;

    @SerializedName("ID")
    private int ruterId;

    @SerializedName("X")
    private int x;

    @SerializedName("Y")
    private int y;

    @SerializedName("Zone")
    private String zone;

    @SerializedName("ShortName")
    private String shortName;

    @SerializedName("IsHub")
    private boolean isHub;

    @SerializedName("Name")
    private String name;

    @SerializedName("District")
    private String district;

    @SerializedName("PlaceType")
    private String placeType;

    @SerializedName("AlightingAllowed")
    private boolean alightingAllowed;

    @SerializedName("BoardingAllowed")
    private boolean boardingAllowed;

    @SerializedName("ArrivalTime")
    private String arrivalTime;

    @SerializedName("DepartureTime")
    private String departureTime;

    private boolean favorite;

    private boolean unwanted;

    private DateTime created;

    public int getRuterId()
    {
        return ruterId;
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

    public boolean isUnwanted()
    {
        return unwanted;
    }

    public int getUnwanted()
    {
        return unwanted ? 1 : 0;
    }

    public void setUnwanted(boolean unwanted)
    {
        this.unwanted = unwanted;
    }

    public void setUnwanted(int unwanted)
    {
        this.unwanted = unwanted == 1;
    }

    public boolean isFavorite()
    {
        return favorite;
    }

    public int getFavorite()
    {
        return favorite ? 1 : 0;
    }
    public void setFavorite(boolean favorite)
    {
        this.favorite = favorite;
    }

    public void setFavorite(int favorite)
    {
        this.favorite = favorite == 1;
    }

    public void setRuterId(int ruterId)
    {
        this.ruterId = ruterId;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public void setZone(String zone)
    {
        this.zone = zone;
    }

    public void setShortName(String shortName)
    {
        this.shortName = shortName;
    }

    public void setHub(boolean hub)
    {
        isHub = hub;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setDistrict(String district)
    {
        this.district = district;
    }

    public void setPlaceType(String placeType)
    {
        this.placeType = placeType;
    }

    public void setAlightingAllowed(boolean alightingAllowed)
    {
        this.alightingAllowed = alightingAllowed;
    }

    public void setBoardingAllowed(boolean boardingAllowed)
    {
        this.boardingAllowed = boardingAllowed;
    }

    public void setArrivalTime(String arrivalTime)
    {
        this.arrivalTime = arrivalTime;
    }

    public void setDepartureTime(String departureTime)
    {
        this.departureTime = departureTime;
    }

    public void setCreated(DateTime created)
    {
        this.created = created;
    }

    public void setDate(Long date)
    {
        this.created = new DateTime(date);
    }

    @Override
    public boolean isExpired()
    {
        if (created == null) return false;
        else return DateTime.now().getMillis() - new DateTime(created).getMillis() > expiration;
    }

    public Long getDate()
    {
        if (created == null) return DateTime.now().getMillis();
        return created.getMillis();
    }

    public String toString()
    {
        return String.format("ID: %d, Name: %s",
                ruterId, name);
    }

    public Stop()
    {
    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeInt(this.ruterId);
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
        dest.writeByte(this.favorite ? (byte) 1 : (byte) 0);
        dest.writeByte(this.unwanted ? (byte) 1 : (byte) 0);
    }

    protected Stop(Parcel in)
    {
        this.ruterId = in.readInt();
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
        this.favorite = in.readByte() != 0;
        this.unwanted = in.readByte() != 0;
    }

    public static final Creator<Stop> CREATOR = new Creator<Stop>()
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
