package net.plastboks.android.ruteravvik.repository;


import net.plastboks.android.ruteravvik.App;
import net.plastboks.android.ruteravvik.api.DateList;
import net.plastboks.android.ruteravvik.api.service.StopService;
import net.plastboks.android.ruteravvik.database.StopDatabase;
import net.plastboks.android.ruteravvik.model.Stop;

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

    public Observable<DateList<Stop>> getStopsRuterRx()
    {

        Observable<DateList<Stop>> stopsFromDb = stopDatabase.getAllRx();
        Observable<DateList<Stop>> stopsFromNetwork = stopService.getStopsRuterRx()
                .doOnNext((stops) -> {
                    if (!stops.isUpToDate()) stopDatabase.addAllRx(stops)
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

    public Observable<DateList<Stop>> getFavoriteStopsRx()
    {
        return stopDatabase.getFavoritesRx()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private Observable<DateList<Stop>> getFavoritesRx()
    {
        return stopDatabase.getFavoritesRx()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<DateList<Stop>> getStopsByLineIdRx(int id)
    {
        return stopService.getStopsByLineIdRx(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
