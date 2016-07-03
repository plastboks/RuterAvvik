package net.plastboks.android.ruteravvik.storage;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import net.plastboks.java.rutersugar.model.Stop;
import net.plastboks.android.ruteravvik.activity.MainActivity;
import net.plastboks.android.ruteravvik.R;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Settings
{
    private static final String KEY = MainActivity.getCurContext().getString(R.string.app_name) +"-settings";

    private static final String FAV_STOPS = "fav_stops";
    private static final String HAS_FAVORITES = "has_favorites";
    private static final String MASKS = "masks";
    private static final String HAS_MASKS = "has_masks";
    private static final String MASKED_MODE = "masked_mode";

    private static Gson gson = new Gson();
    private static SharedPreferences sp = PreferenceManager
            .getDefaultSharedPreferences(MainActivity.getCurContext());

    private static SharedPreferences.Editor w()
    {
        return MainActivity.getCurContext().getSharedPreferences(KEY,
                Context.MODE_PRIVATE).edit();
    }

    private static SharedPreferences r()
    {
        return MainActivity.getCurContext().getSharedPreferences(KEY,
                Context.MODE_PRIVATE);
    }

    public static void setFavorite(Stop stop)
    {
        List<Stop> items;

        if (!hasFavorites()) {
            items = new ArrayList<>();
            w().putBoolean(HAS_FAVORITES, true).apply();
        } else {
            items = getFavorites();
        }

        if (!items.contains(stop)) {
            items.add(stop);
            w().putString(FAV_STOPS, gson.toJson(items)).apply();
        }

    }

    public static List<Stop> getFavorites()
    {
        if (!hasFavorites()) return new ArrayList<>();

        Type listType = new TypeToken<List<Stop>>() {}.getType();
        return gson.fromJson(r().getString(FAV_STOPS, ""), listType);
    }

    public static void removeFavorite(Stop stop)
    {
        if (!hasFavorites()) return;

        List<Stop> items = getFavorites();

        items.remove(stop);

        if (items.isEmpty()) w().putBoolean(HAS_FAVORITES, false);

        w().putString(FAV_STOPS, gson.toJson(items)).apply();
    }

    public static boolean containsFavorite(Stop stop)
    {
        for (Stop s : getFavorites()) {
            if (s.getId() == stop.getId()) return true;
        }

        return false;
    }

    public static boolean hasFavorites()
    {
        return r().getBoolean(HAS_FAVORITES, false);
    }


    public static void addMask(String id)
    {
        List<String> items;

        if (!hasMask()) {
            items = new ArrayList<>();
            w().putBoolean(HAS_MASKS, true).apply();
        } else {
            items = getMasks();
        }

        if (!items.contains(id)) {
            items.add(id);
            Log.d("Masked", id);
            w().putString(MASKS, gson.toJson(items)).apply();
        }
    }

    public static List<String> getMasks()
    {
        if (!hasMask()) return new ArrayList<>();

        Type listType = new TypeToken<List<String>>() {}.getType();
        return gson.fromJson(r().getString(MASKS, ""), listType);
    }

    public static boolean hasMask()
    {
        return r().getBoolean(HAS_MASKS, false);
    }

    public static void removeMask(String id)
    {
        if (!hasMask()) return;

        List<String> items = getMasks();

        Log.d("Unmasked", id);
        items.remove(id);

        if (items.isEmpty()) w().putBoolean(HAS_MASKS, false);

        w().putString(MASKS, gson.toJson(items)).apply();
    }

    public static void setMaskedMode(boolean mode)
    {
        w().putBoolean(MASKED_MODE, mode).apply();
    }

    public static boolean getMaskedMode()
    {
        return r().getBoolean(MASKED_MODE, false);
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
