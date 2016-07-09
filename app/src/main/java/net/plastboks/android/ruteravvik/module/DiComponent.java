package net.plastboks.android.ruteravvik.module;

import net.plastboks.android.ruteravvik.fragment.LinesBySearchListFragment;
import net.plastboks.android.ruteravvik.fragment.StopVisitFragment;
import net.plastboks.android.ruteravvik.fragment.StopsByLineIdFragment;
import net.plastboks.android.ruteravvik.fragment.StopsBySearchFragment;
import net.plastboks.android.ruteravvik.presenter.LinesBySearchPresenter;
import net.plastboks.android.ruteravvik.presenter.SplashPresenter;
import net.plastboks.android.ruteravvik.repository.LinesRepository;
import net.plastboks.android.ruteravvik.repository.MonitoredStopVisitsRepository;
import net.plastboks.android.ruteravvik.repository.StopsRepository;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules={AppModule.class,
                    NetModule.class,
                    DatabaseModule.class})
public interface DiComponent
{
    /**
     * PRESENTERS
     */
    void inject(SplashPresenter splashPresenter);
    void inject(LinesBySearchPresenter linesBySearchPresenter);

    /**
     * FRAGMENTS
     */
    void inject(StopsByLineIdFragment stopsByLineIdFragment);
    void inject(LinesBySearchListFragment linesBySearchListFragment);
    void inject(StopVisitFragment stopVisitFragment);
    void inject(StopsBySearchFragment stopsBySearchFragment);

    /**
     * REPOSITORIES
     */
    void inject(StopsRepository stopsRepository);
    void inject(LinesRepository linesRepository);
    void inject(MonitoredStopVisitsRepository monitoredStopVisitsRepository);
}
