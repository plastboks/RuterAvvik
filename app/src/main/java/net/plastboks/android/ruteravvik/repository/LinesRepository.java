package net.plastboks.android.ruteravvik.repository;

import android.util.Log;

import net.plastboks.android.ruteravvik.App;
import net.plastboks.android.ruteravvik.api.service.LineService;
import net.plastboks.android.ruteravvik.database.LineDatabase;
import net.plastboks.android.ruteravvik.model.Line;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Kudos: http://blog.danlew.net/2015/06/22/loading-data-from-multiple-sources-with-rxjava/
 */
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
        Observable<List<Line>> linesFromNetwork = lineService.getLinesRx()
                .doOnNext((lines) -> {
                    if (lineDatabase.isUpToDate()) lineDatabase.addAllRx(lines)
                            .subscribe((dbLines) -> {
                                // TODO implement event?
                            });
                });

        Observable<List<Line>> linesFromDb = lineDatabase.getAllRx();

        return Observable
                .concat(linesFromDb, linesFromNetwork)
                .first(data -> lineDatabase.isUpToDate())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<List<Line>> getByTypeRx(int type)
    {
        Observable<List<Line>> linesFromNetwork = lineService.getLinesRx()
                .doOnNext((lines) -> {
                    Log.d(TAG, "linesFromNetwork");
                    lineDatabase.addAllRx(lines)
                            .subscribeOn(Schedulers.computation())
                            .subscribe((dbLines) -> {
                                // TODO implement event?
                                Log.d(TAG, "Finished inserting to database");
                            });
                });
                /*.map((lines) -> {
                    for (Line line : lines) if (line.getTransportation() != type) lines.remove(line);
                    return lines;
                });*/


        Observable<List<Line>> linesFromDb = lineDatabase.getByTypeRx(type);

        return Observable
                .concat(linesFromDb, linesFromNetwork)
                .first(data -> lineDatabase.isUpToDate())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    public Observable<List<Line>> getFavoriteRx()
    {
        return lineDatabase.getFavoritesRx()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
