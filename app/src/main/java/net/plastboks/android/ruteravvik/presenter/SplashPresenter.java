package net.plastboks.android.ruteravvik.presenter;

import android.os.Bundle;

import net.plastboks.android.ruteravvik.App;
import net.plastboks.android.ruteravvik.activity.SplashActivity;
import net.plastboks.android.ruteravvik.repository.LinesRepository;
import net.plastboks.android.ruteravvik.repository.StopsRepository;
import net.plastboks.android.ruteravvik.storage.PersistentCache;

import javax.inject.Inject;

public class SplashPresenter extends BasePresenter<SplashActivity>
{
    private static final int REQUEST_ITEMS = 1;

    @Inject protected LinesRepository linesRepository;
    @Inject protected StopsRepository stopsRepository;

    @Override
    protected void onCreate(Bundle savedState)
    {
        super.onCreate(savedState);

        App.getInstance().getDiComponent().inject(this);

        restartableLatestCache(REQUEST_ITEMS,
                () -> linesRepository.getLinesRx(),
                (activity, response) -> PersistentCache.setLines(response),
                (activity, throwable) -> activity.onItemsError(throwable));

        restartableLatestCache(REQUEST_ITEMS,
                () -> stopsRepository.getStopsRuterRx(),
                (activity, response) ->  {
                    PersistentCache.setStops(response);
                    activity.onItemsNext(response);
                },
                (activity, throwable) -> activity.onItemsError(throwable));
    }

    public void request()
    {
        start(REQUEST_ITEMS);
    }
}
