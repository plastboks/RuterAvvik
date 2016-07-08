package net.plastboks.android.ruteravvik.storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import net.plastboks.android.ruteravvik.R;
import net.plastboks.android.ruteravvik.activity.MainActivity;
import net.plastboks.android.ruteravvik.model.Line;
import net.plastboks.android.ruteravvik.model.Stop;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class PersistentCache
{
    private static final String KEY = MainActivity.getCurContext().getString(R.string.app_name) +"-cache";

    private static final String STOPS = "stops";
    private static final String COLORS = "colors";
    private static final String HAS_STOPS = "has_stops";
    private static final String LINES = "lines";
    private static final String HAS_LINES = "has_lines";

    private static Gson gson = new Gson();

    private static SharedPreferences.Editor e()
    {
        return MainActivity.getCurContext().getSharedPreferences(KEY,
                Context.MODE_PRIVATE).edit();
    }

    private static SharedPreferences r()
    {
        return MainActivity.getCurContext().getSharedPreferences(KEY,
                Context.MODE_PRIVATE);
    }

    public static void setStops(List<Stop> stops)
    {
        e().putString(STOPS, gson.toJson(stops)).apply();
        e().putBoolean(HAS_STOPS, true).apply();
    }

    public static List<Stop> getStops()
    {
        Type listType = new TypeToken<List<Stop>>() {}.getType();
        return gson.fromJson(r().getString(STOPS, ""), listType);
    }

    public static boolean hasStops()
    {
        return r().getBoolean(HAS_STOPS, false);
    }

    public static void clearStops()
    {
        e().putString(STOPS, gson.toJson(new LinkedList<Stop>())).apply();
        e().putBoolean(HAS_STOPS, false).apply();
    }

    public static void setLines(List<Line> lines)
    {
        HashMap<String, String> colors = new HashMap<>();

        for (Line line : lines)
            if (!colors.containsKey(line.getName()))
                colors.put(line.getName(), line.getLineColour());

        e().putString(LINES, gson.toJson(lines)).apply();
        e().putString(COLORS, gson.toJson(colors)).apply();
        e().putBoolean(HAS_LINES, true).apply();
    }

    public static List<Line> getLines()
    {
        Type listType = new TypeToken<List<Line>>() {}.getType();
        return gson.fromJson(r().getString(LINES, ""), listType);
    }

    public static boolean hasLines()
    {
        return r().getBoolean(HAS_LINES, false);
    }

    public static void clearLines()
    {
        e().putString(LINES, gson.toJson(new LinkedList<Line>())).apply();
        e().putString(COLORS, gson.toJson(new HashMap<String, String>())).apply();
        e().putBoolean(HAS_LINES, false).apply();
    }

    public static HashMap<String, String> getColors()
    {
        Type listType = new TypeToken<HashMap<String, String>>() {}.getType();
        return gson.fromJson(r().getString(COLORS, ""), listType);
    }
}
