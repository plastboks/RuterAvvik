package net.plastboks.android.ruteravvik.util;

import net.plastboks.android.ruteravvik.App;
import net.plastboks.android.ruteravvik.R;

public enum TransportationType
{
    WALKING(0, R.string.transportation_walking),
    AIR_PORT_BUS(1, R.string.transportation_airportbus),
    BUS(2, R.string.transportation_bus),
    DUMMY(3, R.string.transportation_dummy),
    AIR_PORT_TRAIN(4, R.string.transportation_airporttrain),
    BOAT(5, R.string.transportation_boat),
    TRAIN(6, R.string.transportation_train),
    TRAM(7, R.string.transportation_tram),
    METRO(8, R.string.transportation_metro);


    int key;
    int value;

    TransportationType(int key, int value)
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
        return App.getInstance().getString(value);
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
        return App.getInstance().getString(value);
    }
}
