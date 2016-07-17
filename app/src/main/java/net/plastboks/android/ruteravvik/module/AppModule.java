package net.plastboks.android.ruteravvik.module;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import net.plastboks.android.ruteravvik.storage.PersistentStorage;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule
{
    public Application application;

    public AppModule(Application application)
    {
        this.application = application;
    }

    @Provides
    @Singleton
    public Application providesApplication()
    {
        return application;
    }

    @Provides
    @Singleton
    public SharedPreferences providesSharedPreferences(Application application)
    {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }

    @Provides
    @Singleton
    public PersistentStorage persistentStorage()
    {
        return new PersistentStorage();
    }
}

