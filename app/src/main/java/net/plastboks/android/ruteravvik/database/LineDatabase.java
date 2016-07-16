package net.plastboks.android.ruteravvik.database;

import android.util.Log;

import com.j256.ormlite.dao.Dao;

import net.plastboks.android.ruteravvik.App;
import net.plastboks.android.ruteravvik.model.Line;
import net.plastboks.android.ruteravvik.storage.PersistentStorage;
import net.plastboks.android.ruteravvik.util.ObservableUtil;

import org.joda.time.DateTime;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;

public class LineDatabase extends BaseDatabase<Line>
{
    private static final String TAG = LineDatabase.class.getSimpleName();

    private static final String DB_LINE_LAST_SYNC = "dbLinesLastSync";
    private static final int OUTDATED_TIME = 100*100; // TODO implement real timeout;

    @Inject protected PersistentStorage persistentStorage;
    @Inject protected Dao<Line, Integer> lineDao;

    public LineDatabase()
    {
        App.getInstance().getDiComponent().inject(this);
    }

    @Override
    protected List<Line> getAll()
    {
        try {
            List<Line> lines = lineDao.queryForAll();
            Log.d(TAG, "lines from database count: " + lines.size());
            return lines;
        } catch (SQLException sqle) {
            Log.d(TAG, sqle.getMessage());
        }

        return new ArrayList<>();
    }

    private List<Line> getFavorites()
    {
        try {
            List<Line> lines = lineDao.queryForEq(Line.FIELD_FAVORITE, true);
            Log.d(TAG, "favorite lines from database count: " + lines.size());
            return lines;
        } catch (SQLException sqle) {
            Log.d(TAG, sqle.getMessage());
        }

        return new ArrayList<>();
    }

    public Observable<List<Line>> getFavoritesRx()
    {
        return ObservableUtil.makeObservable(() -> getFavorites());
    }

    private List<Line> getByType(int type)
    {
        try {
            return lineDao.queryForEq(Line.FIELD_TRANSPORTATION, type);
        } catch (SQLException sqle) {
            Log.d(TAG, sqle.getMessage());
        }

        return null;
    }

    public Observable<List<Line>> getByTypeRx(int type)
    {
        return ObservableUtil.makeObservable(() -> getByType(type));
    }

    @Override
    protected Line get(int id)
    {
        try {
            return lineDao.queryForId(id);
        } catch (SQLException sqle) {
            Log.d(TAG, sqle.getMessage());
        }

        return null;
    }

    @Override
    protected Line add(Line line)
    {
        try {
            return lineDao.createIfNotExists(line);
        } catch (SQLException sqle) {
            Log.d(TAG, sqle.getMessage());
        }

        return null;
    }

    @Override
    protected List<Line> addAll(List<Line> lines)
    {
        List<Line> lineList = new ArrayList<>();

        for (Line line : lines) lineList.add(add(line));

        setLastSync();

        return lineList;
    }

    @Override
    public boolean isUpToDate()
    {
        return DateTime.now().getMillis() - persistentStorage.getLong(DB_LINE_LAST_SYNC) > OUTDATED_TIME;
    }

    private void setLastSync()
    {
        persistentStorage.setLong(DB_LINE_LAST_SYNC, DateTime.now().getMillis());
    }
}
