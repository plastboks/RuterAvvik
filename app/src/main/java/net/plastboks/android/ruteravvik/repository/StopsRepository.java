package net.plastboks.android.ruteravvik.repository;

import android.util.Log;

import com.j256.ormlite.dao.Dao;

import net.plastboks.android.ruteravvik.App;
import net.plastboks.android.ruteravvik.api.service.StopService;
import net.plastboks.android.ruteravvik.model.Stop;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class StopsRepository extends BaseRepository
{
    private static final String TAG = StopsRepository.class.getSimpleName();

    @Inject protected StopService stopService;
    @Inject protected Dao<Stop, Integer> stopDao;

    public StopsRepository()
    {
        App.getInstance().getDiComponent().inject(this);
    }

    public Observable<List<Stop>> getStopsRuterRx()
    {

        if (getAllFromDb().size() > 0) {
            Log.d(TAG, "returning stops from database");

            return makeObservable(this::getAllFromDb)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        }

        synchronizeDb();

        return stopService.getStopsRuterRx()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<List<Stop>> getFavoriteStopsRx()
    {
        return makeObservable(() -> getFavoritesFromDb())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private List<Stop> getAllFromDb()
    {
        try {
            List<Stop> stops = stopDao.queryForAll();
            Log.d(TAG, "stops from database count: " + stops.size());
            return stops;
        } catch (SQLException sqle) {
            Log.d(TAG, sqle.getMessage());
        }

        return new ArrayList<>();
    }

    private List<Stop> getFavoritesFromDb()
    {
        try {
            List<Stop> stops = stopDao.queryForEq(Stop.FAVORITE_FIELD, true);
            Log.d(TAG, "favorite lines from database count: " + stops.size());
            return stops;
        } catch (SQLException sqle) {
            Log.d(TAG, sqle.getMessage());
        }

        return new ArrayList<>();
    }


    private void synchronizeDb()
    {
         stopService.getStopsRuterRx()
                .subscribeOn(Schedulers.computation())
                .subscribe(response -> {
                    for (Stop stop: response) {
                        try {
                            stopDao.createIfNotExists(stop);
                        } catch (SQLException sqle) {
                            Log.d(TAG, sqle.getMessage());
                        }
                    }
                    Log.d(TAG, "Done inserting stops");
                });
    }

    public Observable<List<Stop>> getStopsByLineIdRx(int id)
    {
        return stopService.getStopsByLineIdRx(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


}
