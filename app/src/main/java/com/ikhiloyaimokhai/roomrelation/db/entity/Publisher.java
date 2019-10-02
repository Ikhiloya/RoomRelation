package com.ikhiloyaimokhai.roomrelation.db.entity;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "publisher")
public class Publisher {

    @PrimaryKey(autoGenerate = true)
    private Integer id;

    @SerializedName("firstName")
    @Expose
    private String firstName;

    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("company")
    @Expose
    private String company;

    @Ignore
    private List<Author> authors = null;

    /**
     * No args constructor for use in serialization
     */
    public Publisher() {
    }

    /**
     * @param authors
     * @param lastName
     * @param company
     * @param firstName
     */
    @Ignore
    public Publisher(String firstName, String lastName, String company, List<Author> authors) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.company = company;
        this.authors = authors;
    }


    /**
     * constructor that maps the {@link PublisherDetails} to {@link Publisher}
     */
    public Publisher(PublisherDetails publisherDetails) {
        this.id = publisherDetails.getPublisher().getId();
        this.firstName = publisherDetails.getPublisher().getFirstName();
        this.lastName = publisherDetails.getPublisher().getLastName();
        this.company = publisherDetails.getPublisher().getCompany();
        this.authors = this.getAuthors(publisherDetails.getAuthorBookDetails());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }


    public void setAuthor(Author author) {
        if (this.authors == null) {
            this.authors = new ArrayList<>();
        }
        this.authors.add(author);
    }


    private List<Author> getAuthors(List<AuthorBookDetails> authorBookDetails) {
        for (AuthorBookDetails details : authorBookDetails) {
            Author author = details.getAuthor();
            author.setBooks(details.getBooks());
            this.setAuthor(details.getAuthor());
        }
        return this.authors;
    }

    @Override
    public String toString() {
        return "Publisher{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", company='" + company + '\'' +
                ", authors=" + authors +
                '}';
    }
}
