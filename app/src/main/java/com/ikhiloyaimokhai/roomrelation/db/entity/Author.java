package com.ikhiloyaimokhai.roomrelation.db.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import static androidx.room.ForeignKey.CASCADE;

/**
 * Entity used to model the {@link Author} SQLite table in the database
 */
@Entity(tableName = "author")
public class Author {


    @PrimaryKey(autoGenerate = true)
    private Integer id;


    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("lastName")
    @Expose
    private String lastName;

    @Ignore
    private List<Book> books = null;

    /**
     * No args constructor for use in serialization
     */
    public Author() {
    }

    /**
     * @param books
     * @param lastName
     * @param firstName
     */
    @Ignore
    public Author(String firstName, String lastName, List<Book> books) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.books = books;
    }


    @ForeignKey
            (entity = Publisher.class,
                    parentColumns = "id",
                    childColumns = "publisherId",
                    onDelete = CASCADE)
    private Integer publisherId;


    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }


    public Integer getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(Integer publisherId) {
        this.publisherId = publisherId;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", books=" + books +
                ", publisherId=" + publisherId +
                '}';
    }
}
