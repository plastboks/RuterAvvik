package net.plastboks.android.ruteravvik.presenter;


import android.os.Bundle;
import android.support.annotation.NonNull;

import icepick.Icepick;
import nucleus.presenter.RxPresenter;
import nucleus.view.ViewWithPresenter;

/**
 * Kudos: https://github.com/konmik/nucleus/wiki/Restartables-examples
 * https://github.com/konmik/konmik.github.io/wiki/Introduction-to-Model-View-Presenter-on-Android
 */
public abstract class BasePresenter<V extends ViewWithPresenter> extends RxPresenter<V>
{

    @Override
    protected void onCreate(Bundle savedState)
    {
        super.onCreate(savedState);
        Icepick.restoreInstanceState(this, savedState);
    }

    @Override
    protected void onSave(@NonNull Bundle state)
    {
        super.onSave(state);
        Icepick.saveInstanceState(this, state);
    }
}

