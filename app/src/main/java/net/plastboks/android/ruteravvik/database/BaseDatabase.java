package net.plastboks.android.ruteravvik.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import net.plastboks.android.ruteravvik.api.DateList;
import net.plastboks.android.ruteravvik.model.contract.Expires;
import net.plastboks.android.ruteravvik.util.ObservableUtil;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

public abstract class BaseDatabase<T extends Expires>
{
    protected abstract T get(int id);
    protected abstract DbHelper getDbHelper();
    protected abstract BaseContract<T> getContract();
    protected abstract void setLastSync();

    public Observable<DateList<T>> getAllRx()
    {
        return ObservableUtil.makeObservable(() -> getAll());
    }

    public Observable<T> getRx(int id)
    {
        return ObservableUtil.makeObservable(() -> get(id));
    }

    public Observable<Long> addRx(T item)
    {
        return ObservableUtil.makeObservable(() -> add(item));
    }

    public Observable<List<Long>> addAllRx(List<T> items)
    {
        return ObservableUtil.makeObservable(() -> addAll(items));
    }

    protected DateList<T> getAll()
    {
        SQLiteDatabase db = getDbHelper().getWritableDatabase();

        DateList<T> list = new DateList<>();
        String selectQuery = "select * from " + getContract().TABLE_NAME;

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                list.add(getContract().generate(cursor));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return list;
    }

    protected T get(String key, String value)
    {
        SQLiteDatabase db = getDbHelper().getReadableDatabase();

        String where = key + " =?";
        String[] values = { value };

        Cursor cursor = db.query(
                getContract().TABLE_NAME,
                getContract().projection(),
                where,
                values, null, null, null
        );

        if (cursor != null)
            cursor.moveToFirst();

        T item = getContract().generate(cursor);

        cursor.close();
        db.close();

        return item;
    }

    protected DateList<T> getBy(String key, String value)
    {
        DateList<T> items = new DateList<>();
        SQLiteDatabase db = getDbHelper().getReadableDatabase();

        String where = key + " =?";
        String[] values = { value };

        Cursor cursor = db.query(
                getContract().TABLE_NAME,
                getContract().projection(),
                where,
                values, null, null, null
        );

        if (cursor.moveToFirst())
            do {
                items.add(getContract().generate(cursor));
            } while (cursor.moveToNext());

        cursor.close();
        db.close();

        return items;
    }

    protected Long add(T item)
    {
        SQLiteDatabase db = getDbHelper().getWritableDatabase();

        db.beginTransaction();

        Long id = db.insert(
                getContract().TABLE_NAME,
                null,
                getContract().contentValues(item));

        db.setTransactionSuccessful();
        db.endTransaction();
        return id;
    }

    protected List<Long> addAll(List<T> items)
    {
        List<Long> itemList = new ArrayList<>();

        for (T item : items) itemList.add(add(item));

        setLastSync();

        return itemList;
    }
}
