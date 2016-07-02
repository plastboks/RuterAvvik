package net.plastboks.android.ruteravvik.module;

import android.app.Application;

import net.plastboks.java.rutersugar.Ruter;
import net.plastboks.java.rutersugar.service.LineService;
import net.plastboks.java.rutersugar.service.MonitoredStopVisitService;
import net.plastboks.java.rutersugar.service.PlaceService;
import net.plastboks.java.rutersugar.service.StopService;
import net.plastboks.java.rutersugar.service.StreetService;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.HttpUrl;

@Module
public class NetModule
{
    @Inject protected Application application;

    private HttpUrl url;
    private Ruter ruter;
    private int cacheSize = 10 * 1024 * 1024; // 10 MiB

    public NetModule(HttpUrl url)
    {
        this.url = url;
        ruter = new Ruter(url);
    }

    @Provides
    @Singleton
    public Cache providesCache(Application application)
    {
        return new Cache(application.getCacheDir(), cacheSize);
    }

    @Provides
    @Singleton
    public LineService providesLineService(Cache cache)
    {
        return ruter.createService(LineService.class, cache);
    }

    @Provides
    @Singleton
    public MonitoredStopVisitService providesMonitoredStopVisitService(Cache cache)
    {
        return ruter.createService(MonitoredStopVisitService.class, cache);
    }

    @Provides
    @Singleton
    public PlaceService providesPlaceService(Cache cache)
    {
        return ruter.createService(PlaceService.class, cache);
    }

    @Provides
    @Singleton
    public StreetService providesStreetService(Cache cache)
    {
        return ruter.createService(StreetService.class, cache);
    }

    @Provides
    @Singleton
    public StopService providesStopService(Cache cache)
    {
        return ruter.createService(StopService.class, cache);
    }

}
