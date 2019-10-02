package com.ikhiloyaimokhai.roomrelation.db.entity;


import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;


/**
 * A Pojo that is used by {@link Relation} to query for entity relationships
 */
public class PublisherDetails {

    public PublisherDetails() {
    }

    @Embedded
    private Publisher publisher;

    @Relation(parentColumn = "id", entityColumn = "publisherId", entity = Author.class)
    private List<AuthorBookDetails> authorBookDetails;


    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public List<AuthorBookDetails> getAuthorBookDetails() {
        return authorBookDetails;
    }

    public void setAuthorBookDetails(List<AuthorBookDetails> authorBookDetails) {
        this.authorBookDetails = authorBookDetails;
    }

    @Override
    public String toString() {
        return "PublisherDetails{" +
                "publisher=" + publisher +
                ", authorBookDetails=" + authorBookDetails +
                '}';
    }
}
