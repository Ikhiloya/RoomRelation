package com.ikhiloyaimokhai.roomrelation.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.ikhiloyaimokhai.roomrelation.db.dao.PublisherDao;
import com.ikhiloyaimokhai.roomrelation.db.entity.Author;
import com.ikhiloyaimokhai.roomrelation.db.entity.Book;
import com.ikhiloyaimokhai.roomrelation.db.entity.Publisher;

@Database(entities = {Publisher.class, Author.class, Book.class}, version = 1, exportSchema = false)
public abstract class PublisherDatabase extends RoomDatabase {
    private static volatile PublisherDatabase INSTANCE;

    public abstract PublisherDao publisherdao();

    public static PublisherDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (PublisherDatabase.class) {
                if (INSTANCE == null) {
                    // Create database here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            PublisherDatabase.class, "publisher_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}