package net.plastboks.android.ruteravvik.storage;

import android.content.SharedPreferences;

import net.plastboks.android.ruteravvik.App;

import javax.inject.Inject;

public class PersistentStorage
{
    @Inject protected SharedPreferences sharedPreferences;

    public PersistentStorage()
    {
        App.getInstance().getDiComponent().inject(this);
    }

    public void setString(String key, String value)
    {
        sharedPreferences.edit().putString(key, value).apply();
    }

    public String getString(String key, String value)
    {
        return sharedPreferences.getString(key, value);
    }

    public void setBoolean(String key, boolean value)
    {
        sharedPreferences.edit().putBoolean(key, value).apply();
    }

    public boolean getBoolean(String key)
    {
        return sharedPreferences.getBoolean(key, false);
    }

    public void setInt(String key, int value)
    {
        sharedPreferences.edit().putInt(key, value).apply();
    }

    public int getInt(String key)
    {
        return sharedPreferences.getInt(key, -1);
    }

    public void setLong(String key, Long value)
    {
        sharedPreferences.edit().putLong(key, value).apply();
    }

    public Long getLong(String key)
    {
        return sharedPreferences.getLong(key, -1);
    }
}
