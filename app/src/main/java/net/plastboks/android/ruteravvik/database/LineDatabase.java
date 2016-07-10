package net.plastboks.android.ruteravvik.database;

import android.util.Log;

import com.j256.ormlite.dao.Dao;

import net.plastboks.android.ruteravvik.App;
import net.plastboks.android.ruteravvik.model.Line;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class LineDatabase
{
    private static final String TAG = LineDatabase.class.getSimpleName();

    @Inject protected Dao<Line, Integer> lineDao;

    public LineDatabase()
    {
        App.getInstance().getDiComponent().inject(this);
    }

    public List<Line> getAll()
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

    public List<Line> getFavorites()
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

    public List<Line> getByType(int type)
    {
        try {
            return lineDao.queryForEq(Line.FIELD_TRANSPORTATION, type);
        } catch (SQLException sqle) {
            Log.d(TAG, sqle.getMessage());
        }

        return new ArrayList<>();
    }

    public Line get(int id)
    {
        try {
            return lineDao.queryForId(id);
        } catch (SQLException sqle) {
            Log.d(TAG, sqle.getMessage());
        }

        return null;
    }

    public void add(Line line)
    {
        try {
            lineDao.createIfNotExists(line);
        } catch (SQLException sqle) {
            Log.d(TAG, sqle.getMessage());
        }
    }
}
