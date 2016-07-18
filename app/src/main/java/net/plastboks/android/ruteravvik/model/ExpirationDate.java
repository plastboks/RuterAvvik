package net.plastboks.android.ruteravvik.model;

import net.plastboks.android.ruteravvik.model.contract.Expires;

import org.joda.time.DateTime;

abstract class ExpirationDate implements Expires
{
    private DateTime created;

    protected abstract int getExpirationDays();

    public boolean isExpired()
    {
        if (created == null) return false;

        return created.isBefore(DateTime.now().minusDays(getExpirationDays()));
    }

    public Long getCreatedLong()
    {
        if (created == null) return DateTime.now().getMillis();
        else return created.getMillis();
    }

    public DateTime getCreatedDate()
    {
        return created;
    }


    public void setCreatedLong(Long date)
    {
        created = new DateTime(date);
    }

    public void setCreatedDate(DateTime created)
    {
        this.created = created;
    }
}
