package net.plastboks.android.ruteravvik.module;

import net.plastboks.android.ruteravvik.presenter.LinesBySearchPresenter;
import net.plastboks.android.ruteravvik.presenter.SplashPresenter;
import net.plastboks.android.ruteravvik.presenter.StopVisitPresenter;
import net.plastboks.android.ruteravvik.presenter.StopsByLinePresenter;
import net.plastboks.android.ruteravvik.presenter.StopsByLocationPresenter;
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
    void inject(StopsByLocationPresenter stopsByLocationPresenter);
    void inject(StopsByLinePresenter stopsByLinePresenter);
    void inject(StopVisitPresenter stopVisitPresenter);

    /**
     * REPOSITORIES
     */
    void inject(StopsRepository stopsRepository);
    void inject(LinesRepository linesRepository);
    void inject(MonitoredStopVisitsRepository monitoredStopVisitsRepository);
}
