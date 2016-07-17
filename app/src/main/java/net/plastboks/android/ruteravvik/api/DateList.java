package net.plastboks.android.ruteravvik.api;

import net.plastboks.android.ruteravvik.api.contract.ExpirationDate;

import java.util.ArrayList;

public class DateList<T extends ExpirationDate> extends ArrayList<T>
{
    public boolean isUpToDate()
    {
        for (T t : this) if (t.isExpired()) return false;

        return true;
    }
}
