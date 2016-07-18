package net.plastboks.android.ruteravvik.api;

import net.plastboks.android.ruteravvik.model.contract.Expires;

import java.util.ArrayList;

public class DateList<T extends Expires> extends ArrayList<T>
{
    public boolean isUpToDate()
    {
        if (size() == 0) return false;

        for (T t : this) if (t.isExpired()) return false;

        return true;
    }
}
