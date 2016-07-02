package net.plastboks.android.ruteravvik.module;

import net.plastboks.android.ruteravvik.fragment.LinesBySearchFragment;
import net.plastboks.android.ruteravvik.fragment.LoadScreenFragment;
import net.plastboks.android.ruteravvik.fragment.StopVisitFragment;
import net.plastboks.android.ruteravvik.fragment.StopsByLineIdFragment;
import net.plastboks.android.ruteravvik.fragment.StopsBySearchFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules={AppModule.class, NetModule.class})
public interface DiComponent
{
    void inject(StopsByLineIdFragment stopsByLineIdFragment);
    void inject(LinesBySearchFragment linesBySearchFragment);
    void inject(StopVisitFragment stopVisitFragment);
    void inject(LoadScreenFragment loadScreenFragment);
    void inject(StopsBySearchFragment stopsBySearchFragment);
}
