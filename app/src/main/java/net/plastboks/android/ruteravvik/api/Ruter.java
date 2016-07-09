package net.plastboks.android.ruteravvik.api;

import okhttp3.Cache;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Ruter
{
    private OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static Retrofit.Builder builder = new Retrofit.Builder();

    public Ruter(HttpUrl url)
    {
        builder.baseUrl(url)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create());
    }

    public <S> S createService(Class<S> serviceClass, Cache cache)
    {
        httpClient.cache(cache);
        return createService(serviceClass);
    }

    public <S> S createService(Class<S> serviceClass)
    {
        return builder.client(httpClient.build())
                .build()
                .create(serviceClass);
    }
}
