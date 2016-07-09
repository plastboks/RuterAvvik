package net.plastboks.android.ruteravvik.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class RuterDateParser
{
    public static Date toDate(String source)
    {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        try {
            return format.parse(source);
        } catch (ParseException pe) {
            return new GregorianCalendar(1970, 0, 1, 0, 0).getTime();
        }
    }
}
