package net.plastboks.android.ruteravvik.util;

import net.plastboks.android.ruteravvik.R;
import net.plastboks.android.ruteravvik.activity.MainActivity;

/**
 * Created by alex on 11/7/15.
 */
public enum TransportationType
{
    WALKING(0, MainActivity.getCurContext().getString(R.string.transportation_walking)),
    AIR_PORT_BUS(1, MainActivity.getCurContext().getString(R.string.transportation_airportbus)),
    BUS(2, MainActivity.getCurContext().getString(R.string.transportation_bus)),
    DUMMY(3, MainActivity.getCurContext().getString(R.string.transportation_dummy)),
    AIR_PORT_TRAIN(4, MainActivity.getCurContext().getString(R.string.transportation_airporttrain)),
    BOAT(5, MainActivity.getCurContext().getString(R.string.transportation_boat)),
    TRAIN(6, MainActivity.getCurContext().getString(R.string.transportation_train)),
    TRAM(7, MainActivity.getCurContext().getString(R.string.transportation_tram)),
    METRO(8, MainActivity.getCurContext().getString(R.string.transportation_metro));


    int key;
    String value;

    TransportationType(int key, String value)
    {
        this.key = key;
        this.value = value;
    }

    public int getKey()
    {
        return key;
    }

    public String getValue()
    {
        return value;
    }

    public static TransportationType getType(int key)
    {
        for (TransportationType t : TransportationType.values())
            if (t.key == key) return t;

        return DUMMY;
    }

    @Override
    public String toString()
    {
        return value;
    }
}
