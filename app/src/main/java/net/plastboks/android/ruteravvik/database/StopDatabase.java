package net.plastboks.android.ruteravvik.database;

import android.util.Log;

import com.j256.ormlite.dao.Dao;

import net.plastboks.android.ruteravvik.App;
import net.plastboks.android.ruteravvik.model.Stop;
import net.plastboks.android.ruteravvik.storage.PersistentStorage;
import net.plastboks.android.ruteravvik.util.ObservableUtil;

import org.joda.time.DateTime;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;

public class StopDatabase extends BaseDatabase<Stop>
{
    private static final String TAG = StopDatabase.class.getSimpleName();

    private static final String DB_STOP_LAST_SYNC = "dbStopLastSync";
    private static final int OUTDATED_TIME = 100*100; // TODO implement real timeout;

    @Inject protected Dao<Stop, Integer> stopDao;
    @Inject protected PersistentStorage persistentStorage;

    public StopDatabase()
    {
        App.getInstance().getDiComponent().inject(this);
    }

    @Override
    protected List<Stop> getAll()
    {
        try {
            List<Stop> lines = stopDao.queryForAll();
            Log.d(TAG, "stops from database count: " + lines.size());
            return lines;
        } catch (SQLException sqle) {
            Log.d(TAG, sqle.getMessage());
        }

        return null;
    }

    private List<Stop> getFavorites()
    {
        try {
            List<Stop> lines = stopDao.queryForEq(Stop.FIELD_FAVORITE, true);
            Log.d(TAG, "favorite lines from database count: " + lines.size());
            return lines;
        } catch (SQLException sqle) {
            Log.d(TAG, sqle.getMessage());
        }

        return null;
    }

    public Observable<List<Stop>> getFavoritesRx()
    {
        return ObservableUtil.makeObservable(() -> getFavorites());
    }

    @Override
    protected Stop get(int id)
    {
        try {
            return stopDao.queryForId(id);
        } catch (SQLException sqle) {
            Log.d(TAG, sqle.getMessage());
        }

        return null;
    }

    @Override
    protected Stop add(Stop line)
    {
        try {
            return stopDao.createIfNotExists(line);
        } catch (SQLException sqle) {
            Log.d(TAG, sqle.getMessage());
        }

        return null;
    }

    @Override
    protected List<Stop> addAll(List<Stop> stops)
    {
        List<Stop> stopList = new ArrayList<>();

        for (Stop stop : stops) stopList.add(add(stop));

        setLastSync();

        return stopList;
    }

    @Override
    public boolean isUpToDate()
    {
        return DateTime.now().getMillis() - persistentStorage.getLong(DB_STOP_LAST_SYNC) < OUTDATED_TIME;
    }

    private void setLastSync()
    {
        persistentStorage.setLong(DB_STOP_LAST_SYNC, DateTime.now().getMillis());
    }
}
