package com.ikhiloyaimokhai.roomrelation.db.entity;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class AuthorBookDetails {
    public AuthorBookDetails() {
    }

    @Embedded
    public Author author;

    @Relation(parentColumn = "id", entityColumn = "authorId", entity = Book.class)
    private List<Book> books;


    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "AuthorBookDetails{" +
                "author=" + author +
                ", books=" + books +
                '}';
    }
}
