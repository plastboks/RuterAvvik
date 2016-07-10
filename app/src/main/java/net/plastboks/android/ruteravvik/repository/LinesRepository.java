package net.plastboks.android.ruteravvik.repository;

import android.util.Log;

import net.plastboks.android.ruteravvik.App;
import net.plastboks.android.ruteravvik.api.service.LineService;
import net.plastboks.android.ruteravvik.database.LineDatabase;
import net.plastboks.android.ruteravvik.model.Line;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class LinesRepository extends BaseRepository
{
    private static final String TAG = LinesRepository.class.getSimpleName();

    @Inject protected LineService lineService;
    @Inject protected LineDatabase lineDatabase;

    public LinesRepository()
    {
        App.getInstance().getDiComponent().inject(this);
    }

    public Observable<List<Line>> getLinesRx()
    {
        if (lineDatabase.getAll().size() > 0) {
            Log.d(TAG, "returning lines from database");

            return makeObservable(() -> lineDatabase.getAll())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        }

        synchronizeDb();

        return lineService.getLinesRx()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<List<Line>> getLinesByTypeRx(int type)
    {
        return makeObservable(() -> lineDatabase.getByType(type))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    public Observable<List<Line>> getFavoriteLinesRx()
    {
        return makeObservable(() -> lineDatabase.getFavorites())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }



    private void synchronizeDb()
    {
        lineService.getLinesRx()
                .subscribeOn(Schedulers.computation())
                .subscribe(response -> {
                    for (Line line: response) lineDatabase.add(line);
                });
    }
}
