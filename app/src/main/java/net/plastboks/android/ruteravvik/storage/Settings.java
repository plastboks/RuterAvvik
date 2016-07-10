package net.plastboks.android.ruteravvik.storage;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import net.plastboks.android.ruteravvik.App;
import net.plastboks.android.ruteravvik.R;

public class Settings
{
    private static final String KEY = App.getInstance().getString(R.string.app_name) +"-settings";

    private static SharedPreferences sp = PreferenceManager
            .getDefaultSharedPreferences(App.getInstance().getApplicationContext());

    private static SharedPreferences.Editor w()
    {
        return App.getInstance().getSharedPreferences(KEY,
                Context.MODE_PRIVATE).edit();
    }

    private static SharedPreferences r()
    {
        return App.getInstance().getSharedPreferences(KEY,
                Context.MODE_PRIVATE);
    }

    public static int getInt(String key)
    {
        return Integer.parseInt(sp.getString(key, "-1"));
    }

    public static boolean getBool(String key)
    {
        return sp.getBoolean(key, false);
    }

    public static String getString(String key)
    {
        return sp.getString(key, "");
    }

}
