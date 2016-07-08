package net.plastboks.android.ruteravvik.repository;

import net.plastboks.android.ruteravvik.App;
import net.plastboks.android.ruteravvik.api.service.MonitoredStopVisitService;
import net.plastboks.android.ruteravvik.model.MonitoredStopVisit;
import net.plastboks.android.ruteravvik.storage.Settings;
import net.plastboks.android.ruteravvik.util.Datehelper;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MonitoredStopVisitsRepository
{
    @Inject protected MonitoredStopVisitService monitoredStopVisitService;

    public MonitoredStopVisitsRepository()
    {
        App.getInstance().getDiComponent().inject(this);
    }

    public Observable<List<MonitoredStopVisit>> getDeparturesRx(int id)
    {
        return monitoredStopVisitService.getDeparturesRx(id,
                        Datehelper.getDateTime(Settings.getInt("departure_offset")))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
