package com.ikhiloyaimokhai.roomrelation.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.ikhiloyaimokhai.roomrelation.db.entity.Author;
import com.ikhiloyaimokhai.roomrelation.db.entity.Book;
import com.ikhiloyaimokhai.roomrelation.db.entity.Publisher;
import com.ikhiloyaimokhai.roomrelation.db.entity.PublisherDetails;

import java.util.List;

@Dao
public interface PublisherDao {
    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long savePublisher(Publisher publisher);

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void savePublisher(List<Publisher> publishers);

    @Transaction
    @Query("SELECT * FROM publisher WHERE id = :publisherId")
    LiveData<Publisher> getPublisher(int publisherId);


    //Author
    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long saveAuthor(Author author);


    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveAuthors(List<Author> authors);


    //Book
    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveBook(Book book);

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveBooks(List<Book> books);


    //delete
    @Transaction
    @Query("DELETE FROM publisher")
    void deletePublishers();


    /**
     * using {@link androidx.room.Relation} to query for pojo
     */
    @Transaction
    @Query("SELECT * FROM publisher")
    LiveData<List<PublisherDetails>> loadPublishers();
}