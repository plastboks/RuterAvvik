package net.plastboks.android.ruteravvik.fragment;

import android.widget.Toast;

import net.plastboks.android.ruteravvik.presenter.BasePresenter;

import nucleus.view.NucleusSupportFragment;

public abstract class BaseSupportFragment<T extends BasePresenter, S> extends NucleusSupportFragment<T>
{
    public void onItemsError(Throwable throwable)
    {
        Toast.makeText(getActivity().getApplicationContext(),
                throwable.getMessage(), Toast.LENGTH_LONG).show();
    }

    public void onItemsNext(S s)
    {
        loadContent(s);
    }

    public abstract void loadContent(S s);
}
