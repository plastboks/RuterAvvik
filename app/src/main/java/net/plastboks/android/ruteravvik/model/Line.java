package net.plastboks.android.ruteravvik.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Line extends ExpirationDate implements Parcelable
{
    public static final String FIELD_FAVORITE = "favorite";
    public static final String FIELD_UNWANTED = "unwanted";
    public static final String FIELD_TRANSPORTATION = "transportation";

    public static final int EXPIRATION_DAYS = 10;

    private int id;

    @SerializedName("ID")
    private int ruterId;

    @SerializedName("Name")
    private String name;

    @SerializedName("Transportation")
    private int transportation;

    @SerializedName("LineColour")
    private String lineColour;

    private boolean favorite;

    private boolean unwanted;

    public int getRuterId()
    {
        return ruterId;
    }

    public String getName()
    {
        return name;
    }

    public int getTransportation()
    {
        return transportation;
    }

    public String getLineColour()
    {
        return lineColour;
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

    public void setFavorite(int favorite)
    {
        this.favorite = favorite == 1;
    }

    public void setRuterId(int ruterId)
    {
        this.ruterId = ruterId;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setTransportation(int transportation)
    {
        this.transportation = transportation;
    }

    public void setLineColour(String lineColour)
    {
        this.lineColour = lineColour;
    }

    @Override
    public int getExpirationDays()
    {
        return EXPIRATION_DAYS;
    }

    public Line()
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
        dest.writeString(this.name);
        dest.writeInt(this.transportation);
        dest.writeString(this.lineColour);
        dest.writeByte(this.favorite ? (byte) 1 : (byte) 0);
        dest.writeByte(this.unwanted ? (byte) 1 : (byte) 0);
    }

    protected Line(Parcel in)
    {
        this.ruterId = in.readInt();
        this.name = in.readString();
        this.transportation = in.readInt();
        this.lineColour = in.readString();
        this.favorite = in.readByte() != 0;
        this.unwanted = in.readByte() != 0;
    }

    public static final Creator<Line> CREATOR = new Creator<Line>()
    {
        @Override
        public Line createFromParcel(Parcel source)
        {
            return new Line(source);
        }

        @Override
        public Line[] newArray(int size)
        {
            return new Line[size];
        }
    };
}
