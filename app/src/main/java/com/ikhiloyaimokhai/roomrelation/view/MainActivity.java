package com.ikhiloyaimokhai.roomrelation.view;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.ikhiloyaimokhai.roomrelation.App;
import com.ikhiloyaimokhai.roomrelation.R;
import com.ikhiloyaimokhai.roomrelation.db.entity.Publisher;
import com.ikhiloyaimokhai.roomrelation.db.entity.PublisherDetails;
import com.ikhiloyaimokhai.roomrelation.factory.ViewModelFactory;
import com.ikhiloyaimokhai.roomrelation.repository.PublisherRepository;
import com.ikhiloyaimokhai.roomrelation.service.PublisherService;
import com.ikhiloyaimokhai.roomrelation.util.AppExecutors;
import com.ikhiloyaimokhai.roomrelation.util.Status;
import com.ikhiloyaimokhai.roomrelation.viewmodel.PublisherViewModel;

import java.util.List;
import java.util.stream.Collectors;

import timber.log.Timber;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private PublisherViewModel mPublisherViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button getBookBtn = findViewById(R.id.getDataBtn);
        TextView tv = findViewById(R.id.tv);

        // Get the ViewModel
        PublisherService publisherService = App.get().getPublsiherService();
        PublisherRepository mRepository = new PublisherRepository(getApplication(), MainActivity.this, publisherService, new AppExecutors());
        ViewModelFactory factory = new ViewModelFactory(mRepository);
        mPublisherViewModel = ViewModelProviders.of(this, factory).get(PublisherViewModel.class);


        getBookBtn.setOnClickListener(view ->
                mPublisherViewModel.getPublshers().observe(MainActivity.this, listResource -> {
                    if (listResource.status.equals(Status.SUCCESS)) {
                        List<PublisherDetails> data = listResource.data;

                        if (data != null && !data.isEmpty()) {
                            Timber.i(data.toString());
                            List<Publisher> publishers = listResource.data.
                                    stream().map(Publisher::new).
                                    collect(Collectors.toList());

                            Timber.i(publishers.toString());

                            tv.setText(formatResult(publishers));
                        }
                    }
                })
        );
    }

    private String formatResult(List<Publisher> publishers) {
        StringBuilder sb = new StringBuilder();
        publishers.forEach(publisher -> {
            sb.append("Publisher- ").append("\n")
                    .append(publisher.getFirstName())
                    .append(" ").append(publisher.getLastName())
                    .append("\n").append("Company- ")
                    .append(publisher.getCompany())
                    .append("\n\n");

            publisher.getAuthors().forEach(author -> {
                sb.append("Author- ")
                        .append(author.getFirstName())
                        .append(" ")
                        .append(author.getLastName())
                        .append("\n");

                author.getBooks().forEach(book ->
                        sb.append("Book- ")
                                .append(book.getName())
                                .append("\n")
                                .append("Genre ")
                                .append(book.getGenre()));
            });

        });

        return sb.toString();
    }
}
