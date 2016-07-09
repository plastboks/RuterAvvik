package net.plastboks.android.ruteravvik.presenter;

import android.os.Bundle;

import net.plastboks.android.ruteravvik.App;
import net.plastboks.android.ruteravvik.fragment.StopVisitFragment;
import net.plastboks.android.ruteravvik.repository.MonitoredStopVisitsRepository;

import javax.inject.Inject;

import icepick.State;


public class StopVisitPresenter extends BasePresenter<StopVisitFragment>
{
    private static final int REQUEST_ITEMS = 1;

    @Inject protected MonitoredStopVisitsRepository stopVisitsRepository;

    @State protected int stationId;

    @Override
    protected void onCreate(Bundle savedState)
    {
        super.onCreate(savedState);

        App.getInstance().getDiComponent().inject(this);

        restartableLatestCache(REQUEST_ITEMS,
                () -> stopVisitsRepository.getDeparturesRx(stationId),
                (activity, response) ->  activity.onItemsNext(response),
                (activity, throwable) -> activity.onItemsError(throwable));
    }

    public void request(int stationId)
    {
        this.stationId = stationId;
        start(REQUEST_ITEMS);
    }
}
