package com.ikhiloyaimokhai.roomrelation.db.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import static androidx.room.ForeignKey.CASCADE;

/**
 * Entity used to model the {@link Book} SQLite table in the database
 */
@Entity(tableName = "book")
public class Book {


    @PrimaryKey(autoGenerate = true)
    private Integer id;


    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("genre")
    @Expose
    private String genre;

    /**
     * No args constructor for use in serialization
     */
    public Book() {
    }

    /**
     * @param genre
     * @param name
     */
    @Ignore
    public Book(String name, String genre) {
        super();
        this.name = name;
        this.genre = genre;
    }

    public Integer getId() {
        return id;
    }


    @ForeignKey
            (entity = Author.class,
                    parentColumns = "id",
                    childColumns = "authorId",
                    onDelete = CASCADE)
    private Integer authorId;

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", genre='" + genre + '\'' +
                ", authorId=" + authorId +
                '}';
    }
}