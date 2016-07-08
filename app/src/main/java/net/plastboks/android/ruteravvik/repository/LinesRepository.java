package net.plastboks.android.ruteravvik.repository;

import net.plastboks.android.ruteravvik.App;
import net.plastboks.android.ruteravvik.api.service.LineService;
import net.plastboks.android.ruteravvik.model.Line;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class LinesRepository
{
    @Inject protected LineService lineService;

    public LinesRepository()
    {
        App.getInstance().getDiComponent().inject(this);
    }

    public Observable<List<Line>> getLinesRx()
    {
        return lineService.getLinesRx()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
