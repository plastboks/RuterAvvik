package net.plastboks.android.ruteravvik.presenter;

import android.os.Bundle;

import net.plastboks.android.ruteravvik.App;
import net.plastboks.android.ruteravvik.fragment.StopsByLineFragment;
import net.plastboks.android.ruteravvik.repository.StopsRepository;

import javax.inject.Inject;

import icepick.State;


public class StopsByLinePresenter extends BasePresenter<StopsByLineFragment>
{
    private static final int REQUEST_ITEMS = 1;

    @Inject protected StopsRepository stopsRepository;

    @State protected int lineId;

    @Override
    protected void onCreate(Bundle savedState)
    {
        super.onCreate(savedState);

        App.getInstance().getDiComponent().inject(this);

        restartableLatestCache(REQUEST_ITEMS,
                () -> stopsRepository.getStopsByLineIdRx(lineId),
                (activity, response) ->  activity.onItemsNext(response),
                (activity, throwable) -> activity.onItemsError(throwable));
    }

    public void request(int lineId)
    {
        this.lineId = lineId;
        start(REQUEST_ITEMS);
    }
}
