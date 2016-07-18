package net.plastboks.android.ruteravvik.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.provider.BaseColumns;

import net.plastboks.android.ruteravvik.model.Stop;

final class StopContract extends BaseContract<Stop> implements BaseColumns
{
    public static final String TABLE_NAME = "stop";
    public static final String COLUMN_NAME_ID = "id";
    public static final String COLUMN_NAME_RUTER_ID = "ruter_id";
    public static final String COLUMN_NAME_NAME = "name";
    public static final String COLUMN_NAME_SHORT_NAME = "short_name";
    public static final String COLUMN_NAME_ZONE = "zone";
    public static final String COLUMN_NAME_DISTRICT = "district";
    public static final String COLUMN_NAME_PLACE_TYPE = "place_type";
    public static final String COLUMN_NAME_X = "x";
    public static final String COLUMN_NAME_Y = "y";
    public static final String COLUMN_NAME_FAVORITE = "favorite";
    public static final String COLUMN_NAME_UNWANTED = "unwanted";
    public static final String COLUMN_NAME_CREATED = "created";

    public static final String SQL_CREATE_TABLE = String.format(
            "CREATE TABLE %s (" +
                    "%s integer primary key not null, " + // ID
                    "%s integer not null, " + // RUTER_ID
                    "%s varchar(255), " + // NAME
                    "%s varchar(255), " + // SHORT NAME
                    "%s varchar(255), " + // ZONE
                    "%s varchar(255), " + // DISTRICT
                    "%s varchar(255), " + // PLACE_TYPE
                    "%s integer not null, " + // X
                    "%s integer not null, " + // Y
                    "%s integer, " + // FAVORITE
                    "%s integer, " + // UNWANTED
                    "%s integer default date" + // CREATED
                    ")",
            TABLE_NAME,
            COLUMN_NAME_ID,
            COLUMN_NAME_RUTER_ID,
            COLUMN_NAME_NAME,
            COLUMN_NAME_SHORT_NAME,
            COLUMN_NAME_ZONE,
            COLUMN_NAME_DISTRICT,
            COLUMN_NAME_PLACE_TYPE,
            COLUMN_NAME_X,
            COLUMN_NAME_Y,
            COLUMN_NAME_FAVORITE,
            COLUMN_NAME_UNWANTED,
            COLUMN_NAME_CREATED
    );

    public static final String SQL_DELETE_TABLE = "drop table " + TABLE_NAME;

    public StopContract()
    {
        super(TABLE_NAME);
    }

    @Override
    public ContentValues contentValues(Stop stop)
    {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_RUTER_ID, stop.getRuterId());

        values.put(COLUMN_NAME_NAME, stop.getName());
        values.put(COLUMN_NAME_SHORT_NAME, stop.getShortName());
        values.put(COLUMN_NAME_ZONE, stop.getZone());
        values.put(COLUMN_NAME_DISTRICT, stop.getDistrict());
        values.put(COLUMN_NAME_PLACE_TYPE, stop.getPlaceType());

        values.put(COLUMN_NAME_X, stop.getX());
        values.put(COLUMN_NAME_Y, stop.getY());

        values.put(COLUMN_NAME_FAVORITE, stop.getFavorite());
        values.put(COLUMN_NAME_UNWANTED, stop.getUnwanted());
        values.put(COLUMN_NAME_CREATED, stop.getCreatedLong());

        return values;
    }

    @Override
    public String[] projection()
    {
        String[] projection = {
                _ID,
                COLUMN_NAME_RUTER_ID,
                COLUMN_NAME_NAME,
                COLUMN_NAME_SHORT_NAME,
                COLUMN_NAME_ZONE,
                COLUMN_NAME_DISTRICT,
                COLUMN_NAME_PLACE_TYPE,

                COLUMN_NAME_X,
                COLUMN_NAME_Y,

                COLUMN_NAME_FAVORITE,
                COLUMN_NAME_UNWANTED,
                COLUMN_NAME_CREATED
        };
        return projection;
    }

    @Override
    public Stop generate(Cursor cursor)
    {
        Stop stop = new Stop();

        stop.setRuterId(cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_RUTER_ID)));

        stop.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_NAME)));
        stop.setShortName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_SHORT_NAME)));
        stop.setZone(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_ZONE)));
        stop.setDistrict(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_DISTRICT)));
        stop.setPlaceType(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_PLACE_TYPE)));

        stop.setX(cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_X)));
        stop.setY(cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_Y)));

        stop.setFavorite(cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_FAVORITE)));
        stop.setUnwanted(cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_UNWANTED)));
        stop.setCreatedLong(cursor.getLong(cursor.getColumnIndex(COLUMN_NAME_CREATED)));

        return stop;
    }
}
