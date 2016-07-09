package net.plastboks.android.ruteravvik.presenter;

import android.os.Bundle;

import net.plastboks.android.ruteravvik.App;
import net.plastboks.android.ruteravvik.fragment.LinesBySearchFragment;
import net.plastboks.android.ruteravvik.repository.LinesRepository;

import javax.inject.Inject;


public class LinesBySearchPresenter extends BasePresenter<LinesBySearchFragment>
{
    private static final int REQUEST_ITEMS = 1;

    @Inject protected LinesRepository linesRepository;

    @Override
    protected void onCreate(Bundle savedState)
    {
        super.onCreate(savedState);

        App.getInstance().getDiComponent().inject(this);

        restartableLatestCache(REQUEST_ITEMS,
                () -> linesRepository.getLinesRx(),
                (activity, response) ->  activity.onItemsNext(response),
                (activity, throwable) -> activity.onItemsError(throwable));
    }

    public void request()
    {
        start(REQUEST_ITEMS);
    }
}
