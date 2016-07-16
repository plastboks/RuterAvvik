package net.plastboks.android.ruteravvik.database;

import net.plastboks.android.ruteravvik.util.ObservableUtil;

import java.util.List;

import rx.Observable;

public abstract class BaseDatabase<T>
{
    protected abstract T get(int id);
    protected abstract List<T> getAll();
    protected abstract T add(T t);
    protected abstract List<T> addAll(List<T> items);

    public abstract boolean isUpToDate();

    public Observable<List<T>> getAllRx()
    {
        return ObservableUtil.makeObservable(() -> getAll());
    }

    public Observable<T> getRx(int id)
    {
        return ObservableUtil.makeObservable(() -> get(id));
    }

    public Observable<T> addRx(T item)
    {
        return ObservableUtil.makeObservable(() -> add(item));
    }

    public Observable<List<T>> addAllRx(List<T> items)
    {
        return ObservableUtil.makeObservable(() -> addAll(items));
    }
}
