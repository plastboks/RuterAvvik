package net.plastboks.java.ruterapi;

import net.plastboks.java.rutersugar.Ruter;
import net.plastboks.java.rutersugar.service.StopService;
import net.plastboks.java.rutersugar.service.StreetService;

import okhttp3.HttpUrl;

public class BaseTest
{
    private Ruter ruter;

    protected net.plastboks.java.rutersugar.service.DepartureFavouriteService departureFavouriteService;
    protected net.plastboks.java.rutersugar.service.GetAllSalePointsViewService getAllSalePointsViewService;
    protected net.plastboks.java.rutersugar.service.LineService lineService;
    protected net.plastboks.java.rutersugar.service.MonitoredStopVisitService monitoredStopVisitService;
    protected net.plastboks.java.rutersugar.service.PlaceService placeService;
    protected StopService stopService;
    protected StreetService streetService;
    protected net.plastboks.java.rutersugar.service.ValidService validService;

    public BaseTest()
    {
        ruter = new Ruter(new HttpUrl.Builder()
                .scheme("https")
                .host("reisapi.ruter.no")
                .build());

        departureFavouriteService = ruter.createService(net.plastboks.java.rutersugar.service.DepartureFavouriteService.class);
        getAllSalePointsViewService = ruter.createService(net.plastboks.java.rutersugar.service.GetAllSalePointsViewService.class);
        lineService = ruter.createService(net.plastboks.java.rutersugar.service.LineService.class);
        monitoredStopVisitService = ruter.createService(net.plastboks.java.rutersugar.service.MonitoredStopVisitService.class);
        placeService = ruter.createService(net.plastboks.java.rutersugar.service.PlaceService.class);
        stopService = ruter.createService(StopService.class);
        streetService = ruter.createService(StreetService.class);
        validService = ruter.createService(net.plastboks.java.rutersugar.service.ValidService.class);
    }
}
