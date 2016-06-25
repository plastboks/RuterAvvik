package net.plastboks.ruteravvik;

import android.app.Application;
import android.util.Log;

import net.plastboks.ruteravvik.module.AppModule;
import net.plastboks.ruteravvik.module.DaggerDiComponent;
import net.plastboks.ruteravvik.module.DiComponent;
import net.plastboks.ruteravvik.module.NetModule;

import okhttp3.HttpUrl;

public class App extends Application
{
    private static App inst;
    private DiComponent diComponent;

    public static App getInstance()
    {
        return inst;
    }

    @Override
    public void onCreate()
    {
        super.onCreate();

        inst = this;

        diComponent = DaggerDiComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule(new HttpUrl.Builder()
                        .scheme(getString(R.string.ruter_api_scheme))
                        .host(getString(R.string.ruter_api_host))
                        .port(Integer.parseInt(getString(R.string.ruter_api_port)))
                        .build()))
                .build();

        Log.d("APP", "Main app class");
    }

    public DiComponent getDiComponent()
    {
        return diComponent;
    }
}
