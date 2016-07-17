package net.plastboks.android.ruteravvik;

import net.plastboks.android.ruteravvik.api.Ruter;
import net.plastboks.android.ruteravvik.api.service.DepartureFavouriteService;
import net.plastboks.android.ruteravvik.api.service.GetAllSalePointsViewService;
import net.plastboks.android.ruteravvik.api.service.LineService;
import net.plastboks.android.ruteravvik.api.service.MonitoredStopVisitService;
import net.plastboks.android.ruteravvik.api.service.PlaceService;
import net.plastboks.android.ruteravvik.api.service.StopService;
import net.plastboks.android.ruteravvik.api.service.StreetService;
import net.plastboks.android.ruteravvik.api.service.ValidService;

import okhttp3.HttpUrl;

public class BaseTest
{
    private Ruter ruter;

    protected DepartureFavouriteService departureFavouriteService;
    protected GetAllSalePointsViewService getAllSalePointsViewService;
    protected LineService lineService;
    protected MonitoredStopVisitService monitoredStopVisitService;
    protected PlaceService placeService;
    protected StopService stopService;
    protected StreetService streetService;
    protected ValidService validService;

    public BaseTest()
    {
        ruter = new Ruter(new HttpUrl.Builder()
                .scheme("http")
                .host("reisapi.ruter.no")
                .build());


        departureFavouriteService = ruter.createService(DepartureFavouriteService.class);
        getAllSalePointsViewService = ruter.createService(GetAllSalePointsViewService.class);
        lineService = ruter.createService(LineService.class);
        monitoredStopVisitService = ruter.createService(MonitoredStopVisitService.class);
        placeService = ruter.createService(PlaceService.class);
        stopService = ruter.createService(StopService.class);
        streetService = ruter.createService(StreetService.class);
        validService = ruter.createService(ValidService.class);
    }
}
