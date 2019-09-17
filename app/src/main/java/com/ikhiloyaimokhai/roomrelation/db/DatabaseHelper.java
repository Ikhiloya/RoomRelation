package com.ikhiloyaimokhai.roomrelation.db;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.ikhiloyaimokhai.roomrelation.db.dao.PublisherDao;
import com.ikhiloyaimokhai.roomrelation.db.entity.Author;
import com.ikhiloyaimokhai.roomrelation.db.entity.Book;
import com.ikhiloyaimokhai.roomrelation.db.entity.Publisher;

import java.util.List;

import timber.log.Timber;

public class DatabaseHelper {
    private PublisherDao mPublisherDao;

    public DatabaseHelper(PublisherDatabase database) {
        mPublisherDao = database.publisherdao();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void insertAuthorsForPublisher(Long id, List<Author> authors) {
        if (id == null) throw new NullPointerException();
        authors.forEach(author -> author.setPublisherId(id.intValue()));
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void insertBooksForAuthor(Long id, List<Book> books) {
        Timber.d("saving books for Author");
        if (id == null) throw new NullPointerException();
        books.forEach((book) -> book.setAuthorId((id.intValue())));
        mPublisherDao.saveBooks(books);
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public void savePublisher(Publisher publisher) {
        Timber.d("saving user");
        Long id = mPublisherDao.savePublisher(publisher);
        saveAuthors(id, publisher.getAuthors());
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    private void saveAuthors(Long publisherId, List<Author> authors) {
        Timber.d("saving authors for User");
        insertAuthorsForPublisher(publisherId, authors);
        for (Author author : authors) {
            Long id = mPublisherDao.saveAuthor(author);
            insertBooksForAuthor(id, author.getBooks());
        }
    }

}
