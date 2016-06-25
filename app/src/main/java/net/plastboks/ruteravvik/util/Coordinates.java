package net.plastboks.ruteravvik.util;

import com.google.android.gms.maps.model.LatLng;

import net.plastboks.rutersugar.domain.Stop;

public class Coordinates
{

    public static LatLng ruterConvertUTMToLatLong(Stop stop)
    {
        CoordinateConversion cc = new CoordinateConversion();

        String input = String.format("32 V %d %d", stop.getX(), stop.getY());

        double[] coord = cc.utm2LatLon(input);

        return new LatLng(coord[0], coord[1]);
    }

}
