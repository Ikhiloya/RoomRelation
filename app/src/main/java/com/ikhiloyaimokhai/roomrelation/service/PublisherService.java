package com.ikhiloyaimokhai.roomrelation.service;

import androidx.lifecycle.LiveData;

import com.ikhiloyaimokhai.roomrelation.db.entity.Publisher;

import retrofit2.http.GET;

public interface PublisherService {

    @GET("get-publisher")
    LiveData<ApiResponse<Publisher>> getPublishers();
}
