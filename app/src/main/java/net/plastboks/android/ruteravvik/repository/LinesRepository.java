package net.plastboks.android.ruteravvik.repository;

import android.util.Log;

import com.j256.ormlite.dao.Dao;

import net.plastboks.android.ruteravvik.App;
import net.plastboks.android.ruteravvik.api.service.LineService;
import net.plastboks.android.ruteravvik.model.Line;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class LinesRepository extends BaseRepository
{
    private static final String TAG = LinesRepository.class.getSimpleName();

    @Inject protected LineService lineService;
    @Inject protected Dao<Line, Integer> lineDao;

    public LinesRepository()
    {
        App.getInstance().getDiComponent().inject(this);
    }

    public Observable<List<Line>> getLinesRx()
    {
        if (getAllFromDb().size() > 0) {
            Log.d(TAG, "returning lines from database");

            return makeObservable(() -> getAllFromDb())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        }

        synchronizeDb();

        return lineService.getLinesRx()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<List<Line>> getFavoriteLinesRx()
    {
        return makeObservable(() -> getFavoritesFromDb())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private List<Line> getAllFromDb()
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

    private List<Line> getFavoritesFromDb()
    {
        try {
            List<Line> lines = lineDao.queryForEq(Line.FAVORITE_FIELD, true);
            Log.d(TAG, "favorite lines from database count: " + lines.size());
            return lines;
        } catch (SQLException sqle) {
            Log.d(TAG, sqle.getMessage());
        }

        return new ArrayList<>();
    }

    private void synchronizeDb()
    {
        lineService.getLinesRx()
                .subscribeOn(Schedulers.computation())
                .subscribe(response -> {
                    for (Line line: response) {
                        try {
                            lineDao.createIfNotExists(line);
                        } catch (SQLException sqle) {
                            Log.d(TAG, sqle.getMessage());
                        }
                    }
                    Log.d(TAG, "Done inserting lines");
                });
    }
}
