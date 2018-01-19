package ru.mail.aslanisl.testresultant.api;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.mail.aslanisl.testresultant.BuildConfig;

/**
 * Created by Ivan on 19.01.2018.
 */

public class Api {
    private static final String BASE_URL = "http://phisix-api3.appspot.com";

    private static volatile Endpoint mAPIServiceInstance;

    public static Endpoint getApiService() {
        Endpoint localInstance = mAPIServiceInstance;
        if (localInstance == null) {
            synchronized (Endpoint.class) {
                localInstance = mAPIServiceInstance;
                if (localInstance == null) {
                    Retrofit retrofit = Api.getRetrofit();
                    mAPIServiceInstance = localInstance = retrofit.create(Endpoint.class);
                }
            }
        }
        return localInstance;
    }

    static Retrofit getRetrofit() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) builder.addInterceptor(interceptor);
        builder.retryOnConnectionFailure(true);
        builder.connectTimeout(30, TimeUnit.MINUTES);
        builder.readTimeout(30, TimeUnit.MINUTES);
        OkHttpClient client = builder.build();

        Gson gson = new GsonBuilder().create();
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
    }
}