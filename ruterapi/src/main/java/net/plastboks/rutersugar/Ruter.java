package net.plastboks.rutersugar;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Ruter
{
    private OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static Retrofit.Builder builder = new Retrofit.Builder();

    public Ruter(HttpUrl url)
    {
        builder.baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create());
    }

    public <S> S createService(Class<S> serviceClass)
    {
        return builder.client(httpClient.build())
                .build()
                .create(serviceClass);
    }
}
