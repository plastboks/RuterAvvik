package net.plastboks.rutersugar;

import net.plastboks.rutersugar.service.DepartureFavouriteService;
import net.plastboks.rutersugar.service.GetAllSalePointsViewService;
import net.plastboks.rutersugar.service.LineService;
import net.plastboks.rutersugar.service.MonitoredStopVisitService;
import net.plastboks.rutersugar.service.PlaceService;
import net.plastboks.rutersugar.service.StopService;
import net.plastboks.rutersugar.service.StreetService;
import net.plastboks.rutersugar.service.ValidService;

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
                .scheme("https")
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
