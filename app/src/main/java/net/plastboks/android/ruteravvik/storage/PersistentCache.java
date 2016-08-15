package net.plastboks.android.ruteravvik.storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import net.plastboks.android.ruteravvik.App;
import net.plastboks.android.ruteravvik.R;

import java.lang.reflect.Type;
import java.util.HashMap;

public class PersistentCache
{
    private static final String KEY = App.getInstance().getApplicationContext()
            .getString(R.string.app_name) +"-cache";

    private static final String COLORS = "colors";

    private static Gson gson = new Gson();

    private static SharedPreferences.Editor e()
    {
        return App.getInstance().getApplicationContext().getSharedPreferences(KEY,
                Context.MODE_PRIVATE).edit();
    }

    private static SharedPreferences r()
    {
        return App.getInstance().getApplicationContext().getSharedPreferences(KEY,
                Context.MODE_PRIVATE);
    }

    public static HashMap<String, String> getColors()
    {
        Type listType = new TypeToken<HashMap<String, String>>() {}.getType();
        return gson.fromJson(r().getString(COLORS, ""), listType);
    }
}
