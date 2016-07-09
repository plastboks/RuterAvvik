package net.plastboks.android.ruteravvik.presenter;

import android.os.Bundle;

import net.plastboks.android.ruteravvik.fragment.LinesBySearchFragment;


public class StopsByFavoritePresenter extends BasePresenter<LinesBySearchFragment>
{
    private static final int REQUEST_ITEMS = 1;

    @Override
    protected void onCreate(Bundle savedState)
    {
        super.onCreate(savedState);

        // Unused for now.
    }

    public void request()
    {
        start(REQUEST_ITEMS);
    }
}
