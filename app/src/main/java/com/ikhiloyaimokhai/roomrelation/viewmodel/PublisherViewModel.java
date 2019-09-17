package com.ikhiloyaimokhai.roomrelation.viewmodel;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.ikhiloyaimokhai.roomrelation.db.entity.PublisherDetails;
import com.ikhiloyaimokhai.roomrelation.repository.PublisherRepository;
import com.ikhiloyaimokhai.roomrelation.util.Resource;

import java.util.List;


public class PublisherViewModel extends AndroidViewModel {
    private PublisherRepository mRepository;


    public PublisherViewModel(PublisherRepository mRepository) {
        super(mRepository.getApplication());
        this.mRepository = mRepository;
    }


    public LiveData<Resource<List<PublisherDetails>>> getPublshers() {
        return mRepository.getPublshers();
    }


}
