package com.ikhiloyaimokhai.roomrelation.factory;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.ikhiloyaimokhai.roomrelation.repository.PublisherRepository;
import com.ikhiloyaimokhai.roomrelation.viewmodel.PublisherViewModel;

public class ViewModelFactory implements ViewModelProvider.Factory {
    private final PublisherRepository mRepository;

    public ViewModelFactory(PublisherRepository mRepository) {
        this.mRepository = mRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        if (modelClass.isAssignableFrom(PublisherViewModel.class))
            return (T) new PublisherViewModel(mRepository);
        throw new IllegalArgumentException("Unknown ViewModel class");
    }


}

