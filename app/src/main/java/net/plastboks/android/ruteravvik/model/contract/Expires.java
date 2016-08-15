package net.plastboks.android.ruteravvik.model.contract;

import org.joda.time.DateTime;

public interface Expires
{
    boolean isExpired();
    Long getCreatedLong();
    DateTime getCreatedDate();
    void setCreatedLong(Long date);
    void setCreatedDate(DateTime created);
}
