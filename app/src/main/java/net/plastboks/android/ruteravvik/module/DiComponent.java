package net.plastboks.android.ruteravvik.module;

import net.plastboks.android.ruteravvik.database.LineDatabase;
import net.plastboks.android.ruteravvik.database.StopDatabase;
import net.plastboks.android.ruteravvik.presenter.LinesPresenter;
import net.plastboks.android.ruteravvik.presenter.StopVisitPresenter;
import net.plastboks.android.ruteravvik.presenter.StopsByFavoritePresenter;
import net.plastboks.android.ruteravvik.presenter.StopsByLinePresenter;
import net.plastboks.android.ruteravvik.presenter.StopsByLocationPresenter;
import net.plastboks.android.ruteravvik.repository.LinesRepository;
import net.plastboks.android.ruteravvik.repository.MonitoredStopVisitsRepository;
import net.plastboks.android.ruteravvik.repository.StopsRepository;
import net.plastboks.android.ruteravvik.storage.PersistentStorage;

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
    void inject(LinesPresenter linesPresenter);
    void inject(StopsByLocationPresenter stopsByLocationPresenter);
    void inject(StopsByLinePresenter stopsByLinePresenter);
    void inject(StopVisitPresenter stopVisitPresenter);
    void inject(StopsByFavoritePresenter stopsByFavoritePresenter);

    /**
     * Database
     */
    void inject(LineDatabase lineDatabase);
    void inject(StopDatabase stopDatabase);

    /**
     * REPOSITORIES
     */
    void inject(StopsRepository stopsRepository);
    void inject(LinesRepository linesRepository);
    void inject(MonitoredStopVisitsRepository monitoredStopVisitsRepository);

    /**
     * UTILS
     */
    void inject(PersistentStorage persistentStorage);
}
