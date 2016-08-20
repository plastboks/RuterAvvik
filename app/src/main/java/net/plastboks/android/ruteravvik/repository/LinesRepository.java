package net.plastboks.android.ruteravvik.repository;

import android.util.Log;

import net.plastboks.android.ruteravvik.App;
import net.plastboks.android.ruteravvik.api.DateList;
import net.plastboks.android.ruteravvik.api.service.LineService;
import net.plastboks.android.ruteravvik.database.LineDatabase;
import net.plastboks.android.ruteravvik.model.Line;

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

    @Inject
    public LinesRepository()
    {
        App.getInstance().getDiComponent().inject(this);
    }

    public Observable<DateList<Line>> getLinesRx()
    {
        Observable<DateList<Line>> linesFromNetwork = lineService.getLinesRx()
                .doOnNext((lines) -> {
                    if (!lines.isUpToDate())
                        lineDatabase.addAllRx(lines)
                            .subscribe((dbLines) -> {
                                // TODO implement event?
                                Log.d(TAG, "Finished inserting lines");
                            });
                });

        Observable<DateList<Line>> linesFromDb = lineDatabase.getAllRx();

        return Observable
                .concat(linesFromDb, linesFromNetwork)
                .first(data -> data.isUpToDate())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<DateList<Line>> getByTypeRx(int type)
    {
        Observable<DateList<Line>> linesFromNetwork = lineService.getLinesRx()
                .doOnNext((lines) -> {
                    Log.d(TAG, "linesFromNetwork");
                    if (!lines.isUpToDate())
                        Log.d(TAG, "Starting inserting lines to database");
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


        Observable<DateList<Line>> linesFromDb = lineDatabase.getByTypeRx(type);

        return Observable
                .concat(linesFromDb, linesFromNetwork)
                .first(data -> data.isUpToDate())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<DateList<Line>> getFavoriteRx()
    {
        return lineDatabase.getFavoritesRx()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
