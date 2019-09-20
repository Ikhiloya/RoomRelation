package com.ikhiloyaimokhai.roomrelation;

import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ikhiloyaimokhai.roomrelation.db.PublisherDatabase;
import com.ikhiloyaimokhai.roomrelation.db.dao.PublisherDao;
import com.ikhiloyaimokhai.roomrelation.factory.LiveDataCallAdapterFactory;
import com.ikhiloyaimokhai.roomrelation.service.PublisherService;
import com.ikhiloyaimokhai.roomrelation.util.AppExecutors;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

public class App extends Application {
    private static final String TAG = App.class.getSimpleName();
    private static final String BASE_URL = "https://c506b847-e0f6-4214-bd4f-e8525fef90d8.mock.pstmn.io/";
    private PublisherService mPublsiherService;

    private static App INSTANCE;

    private static AppExecutors mAppExecutors;

    private static PublisherDao mPublisherDao;


    public static App get() {
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;

        OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder();

        //Gson Builder
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        Timber.plant(new Timber.DebugTree());

        // HttpLoggingInterceptor
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(message -> Timber.i(message));
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        // OkHttpClient
        OkHttpClient okHttpClient = new OkHttpClient()
                .newBuilder()
                .addInterceptor(httpLoggingInterceptor)
                .build();


        //Retrofit
        Retrofit mRetrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        //PublisherService
        mPublsiherService = mRetrofit.create(PublisherService.class);
        mAppExecutors = new AppExecutors();
        mPublisherDao = PublisherDatabase.getDatabase(getApplicationContext()).publisherdao();


    }


    public PublisherService getPublsiherService() {
        return mPublsiherService;
    }


}
