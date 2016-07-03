package net.plastboks.android.ruteravvik.repository;

import net.plastboks.android.ruteravvik.App;
import net.plastboks.java.rutersugar.model.Stop;
import net.plastboks.java.rutersugar.service.StopService;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class StopsRepository
{
    @Inject protected StopService stopService;

    public StopsRepository()
    {
        App.getInstance().getDiComponent().inject(this);
    }

    public Observable<List<Stop>> getStopsRuterRx()
    {
        return stopService.getStopsRuterRx()
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
