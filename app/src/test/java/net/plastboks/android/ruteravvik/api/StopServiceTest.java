package net.plastboks.android.ruteravvik.api;

import net.plastboks.android.ruteravvik.model.Stop;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class StopServiceTest extends BaseTest
{
    @Test
    public void getStops() throws IOException
    {
        List<Stop> stops = stopService.getStopsRuter().execute().body();

        Assert.assertNotNull(stops);
    }
}
