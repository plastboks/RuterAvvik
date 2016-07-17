package net.plastboks.android.ruteravvik.module;

import android.app.Application;

import net.plastboks.android.ruteravvik.database.DbHelper;
import net.plastboks.android.ruteravvik.database.LineDatabase;
import net.plastboks.android.ruteravvik.database.StopDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule
{
    @Provides
    @Singleton
    public DbHelper providesDbHelper(Application application)
    {
        return new DbHelper(application.getApplicationContext());
    }

    @Provides
    @Singleton
    public LineDatabase providesLineDatabase()
    {
        return new LineDatabase();
    }

    @Provides
    @Singleton
    public StopDatabase providesStopDatabase()
    {
        return new StopDatabase();
    }
}
