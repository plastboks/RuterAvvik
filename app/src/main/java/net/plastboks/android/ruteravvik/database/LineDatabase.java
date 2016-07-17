package net.plastboks.android.ruteravvik.database;


import net.plastboks.android.ruteravvik.App;
import net.plastboks.android.ruteravvik.api.DateList;
import net.plastboks.android.ruteravvik.model.Line;
import net.plastboks.android.ruteravvik.storage.PersistentStorage;
import net.plastboks.android.ruteravvik.util.ObservableUtil;

import org.joda.time.DateTime;

import javax.inject.Inject;

import rx.Observable;

public class LineDatabase extends BaseDatabase<Line>
{
    private static final String TAG = LineDatabase.class.getSimpleName();

    private static final String DB_LINE_LAST_SYNC = "dbLinesLastSync";

    private LineContract lineContract = new LineContract();

    @Inject protected PersistentStorage persistentStorage;
    @Inject protected DbHelper dbHelper;

    public LineDatabase()
    {
        App.getInstance().getDiComponent().inject(this);
    }

    protected DbHelper getDbHelper()
    {
        return dbHelper;
    }

    protected BaseContract<Line> getContract()
    {
        return lineContract;
    }

    private DateList<Line> getFavorites()
    {
        return getBy(LineContract.COLUMN_NAME_FAVORITE, String.valueOf(1));
    }

    public Observable<DateList<Line>> getFavoritesRx()
    {
        return ObservableUtil.makeObservable(() -> getFavorites());
    }

    private DateList<Line> getByType(int type)
    {
        return getBy(LineContract.COLUMN_NAME_TRANSPORTATION, String.valueOf(type));
    }

    public Observable<DateList<Line>> getByTypeRx(int type)
    {
        return ObservableUtil.makeObservable(() -> getByType(type));
    }

    @Override
    protected Line get(int ruterId)
    {
        return super.get(LineContract.COLUMN_NAME_RUTER_ID, String.valueOf(ruterId));
    }

    @Override
    protected void setLastSync()
    {
        persistentStorage.setLong(DB_LINE_LAST_SYNC, DateTime.now().getMillis());
    }
}
