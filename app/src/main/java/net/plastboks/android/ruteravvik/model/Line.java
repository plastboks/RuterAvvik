package net.plastboks.android.ruteravvik.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "lines")
public class Line implements Parcelable
{
    public static final String FIELD_FAVORITE = "favorite";
    public static final String FIELD_UNWANTED = "unwanted";
    public static final String FIELD_TRANSPORTATION = "transportation";

    @DatabaseField(generatedId = true)
    private int id;

    @SerializedName("ID")
    @DatabaseField
    private int ruterId;

    @DatabaseField
    @SerializedName("Name")
    private String name;

    @DatabaseField
    @SerializedName("Transportation")
    private int transportation;

    @DatabaseField
    @SerializedName("LineColour")
    private String lineColour;

    @DatabaseField
    private boolean favorite;

    @DatabaseField
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

    public void setFavorite(boolean favorite)
    {
        this.favorite = favorite;
    }

    public boolean isUnwanted()
    {
        return unwanted;
    }

    public void setUnwanted(boolean unwanted)
    {
        this.unwanted = unwanted;
    }

    public String toString()
    {
        return String.format("ID: %d, Name: %s, Transportation %d, Linecolor: %s",
                ruterId, name.trim(), transportation, lineColour.trim());
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
