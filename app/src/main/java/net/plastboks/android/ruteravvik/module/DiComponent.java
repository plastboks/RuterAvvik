package net.plastboks.android.ruteravvik.module;

import net.plastboks.android.ruteravvik.fragment.LinesBySearchFragment;
import net.plastboks.android.ruteravvik.fragment.LoadScreenFragment;
import net.plastboks.android.ruteravvik.fragment.StopVisitFragment;
import net.plastboks.android.ruteravvik.fragment.StopsByLineIdFragment;
import net.plastboks.android.ruteravvik.fragment.StopsBySearchFragment;
import net.plastboks.android.ruteravvik.repository.LinesRepository;
import net.plastboks.android.ruteravvik.repository.MonitoredStopVisitsRepository;
import net.plastboks.android.ruteravvik.repository.StopsRepository;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules={AppModule.class, NetModule.class})
public interface DiComponent
{
    /**
     * FRAGMENTS
     */
    void inject(StopsByLineIdFragment stopsByLineIdFragment);
    void inject(LinesBySearchFragment linesBySearchFragment);
    void inject(StopVisitFragment stopVisitFragment);
    void inject(LoadScreenFragment loadScreenFragment);
    void inject(StopsBySearchFragment stopsBySearchFragment);

    /**
     * REPOSITORIES
     */
    void inject(StopsRepository stopsRepository);
    void inject(LinesRepository linesRepository);
    void inject(MonitoredStopVisitsRepository monitoredStopVisitsRepository);
}
