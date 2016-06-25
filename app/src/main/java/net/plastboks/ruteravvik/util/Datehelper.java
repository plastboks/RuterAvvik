package net.plastboks.ruteravvik.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Datehelper
{
    public static String getTime(Date date)
    {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        return format.format(date);
    }

    public static String getTimeSecond(Date date)
    {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        return format.format(date);
    }

    public static int getSecondsDiff(Date a, Date b)
    {
        return (int)(b.getTime() - a.getTime()) / 1000;
    }

    public static String getTimeDiff(Date a, Date b)
    {
        int dateDiff = getSecondsDiff(a, b);

        if (dateDiff == 0) return "";

        int min = dateDiff / 60;
        int sec = dateDiff % 60;
        String sign = min >= 0 ? "+" : "-";

        return String.format("%s%02d:%02d",
                sign, min, sec);
    }

    public static String getDateTime(int minutesIncrement)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

        return dateFormat.format(new Date(new Date().getTime() + minutesIncrement*60*1000));
    }

    public static boolean after(Date d, int minOffset)
    {
        return d.getTime() > new Date().getTime() + minOffset*60*1000;
    }

    public static boolean before(Date d, int minOffset)
    {
        return d.getTime() < new Date().getTime() + minOffset*60*1000;
    }
}
