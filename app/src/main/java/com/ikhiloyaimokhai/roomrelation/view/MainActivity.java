package com.ikhiloyaimokhai.roomrelation.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.ikhiloyaimokhai.roomrelation.App;
import com.ikhiloyaimokhai.roomrelation.R;
import com.ikhiloyaimokhai.roomrelation.factory.ViewModelFactory;
import com.ikhiloyaimokhai.roomrelation.repository.PublisherRepository;
import com.ikhiloyaimokhai.roomrelation.service.PublisherService;
import com.ikhiloyaimokhai.roomrelation.util.AppExecutors;
import com.ikhiloyaimokhai.roomrelation.viewmodel.PublisherViewModel;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private PublisherViewModel mPublisherViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Get the ViewModel
        PublisherService publisherService  = App.get().getPublsiherService();
        PublisherRepository mRepository = new PublisherRepository(getApplication(), MainActivity.this, publisherService, new AppExecutors());
        ViewModelFactory factory = new ViewModelFactory(mRepository);
        mPublisherViewModel = ViewModelProviders.of(this, factory).get(PublisherViewModel.class);



    }
}
