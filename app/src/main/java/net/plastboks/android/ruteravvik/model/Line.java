package net.plastboks.android.ruteravvik.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "lines")
public class Line implements Parcelable
{
    @DatabaseField(id = true)
    @SerializedName("ID")
    private int id;

    @DatabaseField
    @SerializedName("Name")
    private String name;

    @DatabaseField
    @SerializedName("Transportation")
    private int transportation;

    @DatabaseField
    @SerializedName("LineColour")
    private String lineColour;

    public int getId()
    {
        return id;
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

    public String toString()
    {
        return String.format("ID: %d, Name: %s, Transportation %d, Linecolor: %s",
                id, name.trim(), transportation, lineColour.trim());
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
        dest.writeInt(this.transportation);
        dest.writeString(this.lineColour);
    }

    public Line()
    {
    }

    protected Line(Parcel in)
    {
        this.id = in.readInt();
        this.name = in.readString();
        this.transportation = in.readInt();
        this.lineColour = in.readString();
    }

    public static final Parcelable.Creator<Line> CREATOR = new Parcelable.Creator<Line>()
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
