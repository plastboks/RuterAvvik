package net.plastboks.android.ruteravvik.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.provider.BaseColumns;

import net.plastboks.android.ruteravvik.model.Line;

final class LineContract extends BaseContract<Line> implements BaseColumns
{
    public static final String TABLE_NAME = "line";
    public static final String COLUMN_NAME_ID = "id";
    public static final String COLUMN_NAME_RUTER_ID = "ruter_id";
    public static final String COLUMN_NAME_NAME = "name";
    public static final String COLUMN_NAME_COLOR = "color";
    public static final String COLUMN_NAME_TRANSPORTATION = "transportation";
    public static final String COLUMN_NAME_FAVORITE = "favorite";
    public static final String COLUMN_NAME_UNWANTED = "unwanted";
    public static final String COLUMN_NAME_CREATED = "created";

    public static final String SQL_CREATE_TABLE = String.format(
            "CREATE TABLE %s (" +
                    "%s integer primary key not null, " + // ID
                    "%s integer not null, " + // RUTER_ID
                    "%s varchar(255), " + // NAME
                    "%s varchar(255), " + // COLOR
                    "%s integer not null, " + // TRANSPORTATION
                    "%s integer, " + // FAVORITE
                    "%s integer, " + // UNWANTED
                    "%s integer default date" + // CREATED
                    ")",
            TABLE_NAME,
            COLUMN_NAME_ID,
            COLUMN_NAME_RUTER_ID,
            COLUMN_NAME_NAME,
            COLUMN_NAME_COLOR,
            COLUMN_NAME_TRANSPORTATION,
            COLUMN_NAME_FAVORITE,
            COLUMN_NAME_UNWANTED,
            COLUMN_NAME_CREATED
    );

    public static final String SQL_DELETE_TABLE = "drop table " + TABLE_NAME;

    public LineContract()
    {
        super(TABLE_NAME);
    }

    @Override
    public ContentValues contentValues(Line line)
    {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_RUTER_ID, line.getRuterId());
        values.put(COLUMN_NAME_NAME, line.getName());
        values.put(COLUMN_NAME_TRANSPORTATION, line.getTransportation());
        values.put(COLUMN_NAME_COLOR, line.getLineColour());
        values.put(COLUMN_NAME_FAVORITE, line.getFavorite());
        values.put(COLUMN_NAME_UNWANTED, line.getUnwanted());
        values.put(COLUMN_NAME_CREATED, line.getCreatedLong());

        return values;
    }

    @Override
    public String[] projection()
    {
        String[] projection = {
                //_ID,
                COLUMN_NAME_RUTER_ID,
                COLUMN_NAME_NAME,
                COLUMN_NAME_COLOR,
                COLUMN_NAME_TRANSPORTATION,
                COLUMN_NAME_FAVORITE,
                COLUMN_NAME_UNWANTED,
                COLUMN_NAME_CREATED
        };
        return projection;
    }

    @Override
    public Line generate(Cursor cursor)
    {
        Line line = new Line();

        line.setRuterId(cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_RUTER_ID)));
        line.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_NAME)));
        line.setTransportation(cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_TRANSPORTATION)));
        line.setLineColour(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_COLOR)));
        line.setFavorite(cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_FAVORITE)));
        line.setUnwanted(cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_UNWANTED)));
        line.setCreatedLong(cursor.getLong(cursor.getColumnIndex(COLUMN_NAME_CREATED)));

        return line;
    }
}
