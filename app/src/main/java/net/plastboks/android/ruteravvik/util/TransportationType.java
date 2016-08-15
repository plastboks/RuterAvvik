package net.plastboks.android.ruteravvik.util;

import android.graphics.drawable.Drawable;

import net.plastboks.android.ruteravvik.App;
import net.plastboks.android.ruteravvik.R;

public enum TransportationType
{
    WALKING(0,
            R.string.transportation_walking,
            R.drawable.ic_sync_black_24dp),

    AIR_PORT_BUS(1,
            R.string.transportation_airportbus,
            R.drawable.ic_directions_bus_white_24dp),

    BUS(2,
            R.string.transportation_bus,
            R.drawable.ic_directions_bus_white_24dp),

    DUMMY(3,
            R.string.transportation_dummy,
            R.drawable.ic_sync_black_24dp),

    AIR_PORT_TRAIN(4,
            R.string.transportation_airporttrain,
            R.drawable.ic_directions_railway_white_24dp),

    BOAT(5,
            R.string.transportation_boat,
            R.drawable.ic_directions_boat_white_24dp),

    TRAIN(6,
            R.string.transportation_train,
            R.drawable.ic_train_white_24dp),

    TRAM(7,
            R.string.transportation_tram,
            R.drawable.ic_tram_white_24dp),

    METRO(8,
            R.string.transportation_metro,
            R.drawable.ic_directions_subway_white_24dp);


    int key;
    int value;
    int resource;

    TransportationType(int key, int value, int resource)
    {
        this.key = key;
        this.value = value;
        this.resource = resource;
    }

    public int getKey()
    {
        return key;
    }

    public String getValue()
    {
        return App.getInstance().getString(value);
    }

    public Drawable getDrawable()
    {
        return App.getInstance().getDrawable(resource);
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
