package net.plastboks.android.ruteravvik.util;

import android.util.Log;

import java.util.concurrent.Callable;

import rx.Observable;
import rx.Subscriber;

public class ObservableUtil
{
    private static final String TAG = ObservableUtil.class.getSimpleName();

    public static <T> Observable<T> makeObservable(final Callable<T> func) {
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
                            Log.e(TAG, ex.getMessage());
                        }
                    }
                });
    }
}

