package net.plastboks.android.ruteravvik.presenter;

import android.os.Bundle;

import net.plastboks.android.ruteravvik.App;
import net.plastboks.android.ruteravvik.fragment.LinesFragment;
import net.plastboks.android.ruteravvik.repository.LinesRepository;

import javax.inject.Inject;

import icepick.State;


public class LinesPresenter extends BasePresenter<LinesFragment>
{
    private static final int REQUEST_ITEMS = 1;

    @Inject protected LinesRepository linesRepository;

    @State protected int transportationType;

    @Override
    protected void onCreate(Bundle savedState)
    {
        super.onCreate(savedState);

        App.getInstance().getDiComponent().inject(this);

        restartableLatestCache(REQUEST_ITEMS,
                () -> linesRepository.getLinesByTypeRx(transportationType),
                (activity, response) ->  activity.onItemsNext(response),
                (activity, throwable) -> activity.onItemsError(throwable));
    }

    public void request(int transportationType)
    {
        this.transportationType = transportationType;
        start(REQUEST_ITEMS);
    }
}
