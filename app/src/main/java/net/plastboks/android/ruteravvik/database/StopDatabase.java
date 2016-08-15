package net.plastboks.android.ruteravvik.database;

import net.plastboks.android.ruteravvik.App;
import net.plastboks.android.ruteravvik.api.DateList;
import net.plastboks.android.ruteravvik.model.Stop;
import net.plastboks.android.ruteravvik.storage.PersistentStorage;
import net.plastboks.android.ruteravvik.util.ObservableUtil;

import org.joda.time.DateTime;

import javax.inject.Inject;

import rx.Observable;

public class StopDatabase extends BaseDatabase<Stop>
{
    private static final String TAG = StopDatabase.class.getSimpleName();

    private static final String DB_STOP_LAST_SYNC = "dbStopLastSync";

    private StopContract stopContract = new StopContract();

    @Inject protected PersistentStorage persistentStorage;
    @Inject protected DbHelper dbHelper;

    public StopDatabase()
    {
        App.getInstance().getDiComponent().inject(this);
    }

    protected DbHelper getDbHelper()
    {
        return dbHelper;
    }

    protected BaseContract<Stop> getContract()
    {
        return stopContract;
    }

    private DateList<Stop> getFavorites()
    {
        return super.getBy(StopContract.COLUMN_NAME_FAVORITE, String.valueOf(1));
    }

    public Observable<DateList<Stop>> getFavoritesRx()
    {
        return ObservableUtil.makeObservable(() -> getFavorites());
    }

    @Override
    protected Stop get(int ruterId)
    {
        return super.get(StopContract.COLUMN_NAME_RUTER_ID, String.valueOf(ruterId));
    }

    @Override
    protected void setLastSync()
    {
        persistentStorage.setLong(DB_STOP_LAST_SYNC, DateTime.now().getMillis());
    }
}
