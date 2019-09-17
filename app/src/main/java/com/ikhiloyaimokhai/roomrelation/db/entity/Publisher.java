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
    private List<Author> author = null;

    /**
     * No args constructor for use in serialization
     */
    public Publisher() {
    }

    /**
     * @param author
     * @param lastName
     * @param company
     * @param firstName
     */
    @Ignore
    public Publisher(String firstName, String lastName, String company, List<Author> author) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.company = company;
        this.author = author;
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
        return author;
    }

    public void setAuthor(List<Author> author) {
        this.author = author;
    }


    public void setAuthor(Author author) {
        if (this.author == null) {
            this.author = new ArrayList<>();
        }
        this.author.add(author);
    }

    @Override
    public String toString() {
        return "Publisher{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", company='" + company + '\'' +
                ", author=" + author +
                '}';
    }
}
