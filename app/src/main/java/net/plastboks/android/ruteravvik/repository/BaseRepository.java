package net.plastboks.android.ruteravvik.repository;

import android.util.Log;

import java.util.concurrent.Callable;

import rx.Observable;
import rx.Subscriber;

/**
 * Copied from: http://stackoverflow.com/questions/32672012/getting-started-with-rxjava-with-sqlite
 */
public abstract class BaseRepository
{
    private static final String TAG = BaseRepository.class.getSimpleName();

    protected static <T> Observable<T> makeObservable(final Callable<T> func) {
        return Observable.create(
                new Observable.OnSubscribe<T>() {
                    @Override
                    public void call(Subscriber<? super T> subscriber) {
                        try {
                            T observed = func.call();
                            if (observed != null) { // to make defaultIfEmpty work
                                subscriber.onNext(observed);
                            }
                            subscriber.onCompleted();
                        } catch(Exception ex) {
                            Log.e(TAG, "Error reading from the database", ex);
                        }
                    }
                });
    }
}
