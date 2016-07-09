package net.plastboks.android.ruteravvik.activity;

import android.widget.Toast;

import net.plastboks.android.ruteravvik.presenter.BasePresenter;

import nucleus.view.NucleusAppCompatActivity;

public abstract class BaseActivity<T extends BasePresenter, S> extends NucleusAppCompatActivity<T>
{
    public void onItemsError(Throwable throwable)
    {
        Toast.makeText(this, throwable.getMessage(), Toast.LENGTH_LONG).show();
    }

    public void onItemsNext(S s)
    {
        loadContent(s);
    }

    public abstract void loadContent(S s);
}
