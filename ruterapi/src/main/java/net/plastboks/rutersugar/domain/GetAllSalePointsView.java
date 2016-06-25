package net.plastboks.rutersugar.domain;

import com.google.gson.annotations.SerializedName;
import net.plastboks.rutersugar.util.RuterDateParser;

import java.util.Date;

public class GetAllSalePointsView // thank you ruter...
{
    @SerializedName("SalePointID")
    private int salePointID;
    @SerializedName("SalePointType")
    private int salePointType;
    @SerializedName("ChainID")
    private int chainID;
    @SerializedName("KbNr")
    private float kbNr;
    @SerializedName("Chain")
    private String chain;
    @SerializedName("Name")
    private String name;
    @SerializedName("Address")
    private String address;
    @SerializedName("ZipCode")
    private String zipCode;
    @SerializedName("ZipCity")
    private String zipCity;
    @SerializedName("Latitude")
    private float latitude;
    @SerializedName("Longitude")
    private float longitude;
    @SerializedName("Utm33CoordinateX")
    private int utm33CoordinateX;
    @SerializedName("Utm33CoordinateY")
    private int utm33CoordinateY;
    @SerializedName("DateTime")
    private String dateTime;

    public int getSalePointID()
    {
        return salePointID;
    }

    public int getSalePointType()
    {
        return salePointType;
    }

    public int getChainID()
    {
        return chainID;
    }

    public float getKbNr()
    {
        return kbNr;
    }

    public String getChain()
    {
        return chain;
    }

    public String getName()
    {
        return name;
    }

    public String getAddress()
    {
        return address;
    }

    public String getZipCode()
    {
        return zipCode;
    }

    public String getZipCity()
    {
        return zipCity;
    }

    public float getLatitude()
    {
        return latitude;
    }

    public float getLongitude()
    {
        return longitude;
    }

    public int getUtm33CoordinateX()
    {
        return utm33CoordinateX;
    }

    public int getUtm33CoordinateY()
    {
        return utm33CoordinateY;
    }

    public Date getDateTime()
    {
        return RuterDateParser.toDate(dateTime);
    }
}
