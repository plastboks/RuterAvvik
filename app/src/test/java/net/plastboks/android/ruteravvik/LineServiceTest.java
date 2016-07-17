package net.plastboks.android.ruteravvik;


import net.plastboks.android.ruteravvik.api.DateList;
import net.plastboks.android.ruteravvik.model.Line;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class LineServiceTest extends BaseTest
{
    @Test
    public void getLines() throws IOException
    {
        List<Line> lines = lineService.getLines().execute().body();

        Assert.assertNotNull(lines);
    }
}
