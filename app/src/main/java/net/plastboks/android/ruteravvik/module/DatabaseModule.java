package net.plastboks.android.ruteravvik.module;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import net.plastboks.android.ruteravvik.model.Line;
import net.plastboks.android.ruteravvik.model.MonitoredStopVisit;
import net.plastboks.android.ruteravvik.model.Stop;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule extends OrmLiteSqliteOpenHelper
{
    private static final String DATABASE_NAME = "ruteravvik.db";
    private static final int DATABASE_VERSION = 1;

    private Dao<Line, Integer> lineDao = null;
    private RuntimeExceptionDao<Line, Integer> lineRuntimeDao = null;

    private List<Class> classes = Arrays.asList(
            Line.class,
            Stop.class,
            MonitoredStopVisit.class
    );

    public DatabaseModule(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource)
    {
        try {
            for (Class c : classes) TableUtils.createTable(connectionSource, c);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion)
    {
        try {
            for (Class c : classes) TableUtils.dropTable(connectionSource, c, true);
            onCreate(db, connectionSource);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Provides
    @Singleton
    public Dao<Line, Integer> producesDao()
    {

        if (lineDao == null) {
            try {
                lineDao = getDao(Line.class);
            } catch (SQLException sqle) {}
        }
        return lineDao;
    }

    public RuntimeExceptionDao<Line, Integer> getSimpleDataDao()
    {
        if (lineRuntimeDao == null) {
            lineRuntimeDao = getRuntimeExceptionDao(Line.class);
        }
        return lineRuntimeDao;
    }

    @Override
    public void close()
    {
        super.close();
        lineDao = null;
        lineRuntimeDao = null;
    }
}
