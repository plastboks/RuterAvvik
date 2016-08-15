package net.plastboks.android.ruteravvik.presenter;

import android.os.Bundle;

import net.plastboks.android.ruteravvik.App;
import net.plastboks.android.ruteravvik.fragment.StopsByLocationFragment;
import net.plastboks.android.ruteravvik.repository.StopsRepository;

import javax.inject.Inject;


public class StopsByLocationPresenter extends BasePresenter<StopsByLocationFragment>
{
    private static final int REQUEST_ITEMS = 1;

    @Inject protected StopsRepository stopsRepository;

    @Override
    protected void onCreate(Bundle savedState)
    {
        super.onCreate(savedState);

        App.getInstance().getDiComponent().inject(this);

        restartableLatestCache(REQUEST_ITEMS,
                () -> stopsRepository.getStopsRuterRx(),
                (activity, response) ->  activity.onItemsNext(response),
                (activity, throwable) -> activity.onItemsError(throwable));
    }

    public void request()
    {
        start(REQUEST_ITEMS);
    }
}
