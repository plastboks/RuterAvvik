package net.plastboks.android.ruteravvik.database;

import android.content.ContentValues;
import android.database.Cursor;

abstract class BaseContract<T>
{
    public final String TABLE_NAME;

    public abstract ContentValues contentValues(T t);
    public abstract String[] projection();
    public abstract T generate(Cursor cursor);

    BaseContract(String table_name)
    {
        TABLE_NAME = table_name;
    }
}
