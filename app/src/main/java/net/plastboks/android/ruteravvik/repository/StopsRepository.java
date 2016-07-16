package net.plastboks.android.ruteravvik.repository;


import net.plastboks.android.ruteravvik.App;
import net.plastboks.android.ruteravvik.api.service.StopService;
import net.plastboks.android.ruteravvik.database.StopDatabase;
import net.plastboks.android.ruteravvik.model.Stop;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class StopsRepository extends BaseRepository
{
    private static final String TAG = StopsRepository.class.getSimpleName();

    @Inject protected StopService stopService;
    @Inject protected StopDatabase stopDatabase;

    public StopsRepository()
    {
        App.getInstance().getDiComponent().inject(this);
    }

    public Observable<List<Stop>> getStopsRuterRx()
    {

        Observable<List<Stop>> stopsFromDb = stopDatabase.getAllRx();
        Observable<List<Stop>> stopsFromNetwork = stopService.getStopsRuterRx()
                .doOnNext((stops) -> {
                    if (stopDatabase.isUpToDate()) stopDatabase.addAllRx(stops)
                            .subscribe((dbStops) -> {
                                // TODO send out event?
                            });
                });

        return Observable
                .concat(stopsFromDb, stopsFromNetwork)
                .first()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<List<Stop>> getFavoriteStopsRx()
    {
        return stopDatabase.getFavoritesRx()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private Observable<List<Stop>> getFavoritesRx()
    {
        return stopDatabase.getFavoritesRx()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<List<Stop>> getStopsByLineIdRx(int id)
    {
        return stopService.getStopsByLineIdRx(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
