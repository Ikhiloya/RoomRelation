package com.ikhiloyaimokhai.roomrelation.repository;

import android.app.Activity;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;

import com.ikhiloyaimokhai.roomrelation.db.DatabaseHelper;
import com.ikhiloyaimokhai.roomrelation.db.PublisherDatabase;
import com.ikhiloyaimokhai.roomrelation.db.dao.PublisherDao;
import com.ikhiloyaimokhai.roomrelation.db.entity.Publisher;
import com.ikhiloyaimokhai.roomrelation.db.entity.PublisherDetails;
import com.ikhiloyaimokhai.roomrelation.service.ApiResponse;
import com.ikhiloyaimokhai.roomrelation.service.PublisherService;
import com.ikhiloyaimokhai.roomrelation.util.AppExecutors;
import com.ikhiloyaimokhai.roomrelation.util.NetworkBoundResource;
import com.ikhiloyaimokhai.roomrelation.util.Resource;

import java.util.List;

import timber.log.Timber;

public class PublisherRepository {
    private static PublisherDao mPublisherDao;
    private static DatabaseHelper mDbHelper;
    private static PublisherService mPublisherService;
    private final AppExecutors appExecutors;
    private final Activity activity;
    private final android.app.Application application;
    private static String LOG_TAG = PublisherRepository.class.getSimpleName();


    public PublisherRepository(android.app.Application application, Activity activity, PublisherService publisherService,
                               AppExecutors appExecutors) {
        this.application = application;
        this.activity = activity;
        PublisherDatabase db = PublisherDatabase.getDatabase(application);
        mPublisherDao = db.publisherdao();
        mDbHelper = new DatabaseHelper(db);
        mPublisherService = publisherService;
        this.appExecutors = appExecutors;
    }


    public LiveData<Resource<List<PublisherDetails>>> getPublshers() {
        /**
         * List<PublisherDetails> is the [ResultType]
         * LoginModel is the [RequestType]
         */
        return new NetworkBoundResource<List<PublisherDetails>, Publisher>(appExecutors) {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            protected void saveCallResult(@NonNull Publisher publisher) {
                Timber.d("call to delete users in db");
                mPublisherDao.deletePublishers();
                Timber.d("call to insert results to db");
                mDbHelper.savePublisher(publisher);
            }

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            protected boolean shouldFetch(@Nullable List<PublisherDetails> data) {
                return true;
            }

            @NonNull
            @Override
            protected LiveData<List<PublisherDetails>> loadFromDb() {
                Timber.d(" call to load from db");
                return mPublisherDao.loadPublishers();
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<Publisher>> createCall() {
                Timber.d("creating a call to network");
                return mPublisherService.getPublishers();
            }

            @Override
            protected Publisher processResponse(ApiResponse<Publisher> response) {
                return super.processResponse(response);
            }
        }.asLiveData();
    }


    public android.app.Application getApplication() {
        return application;
    }


}
