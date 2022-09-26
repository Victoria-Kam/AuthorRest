package com.example.hibernatetest.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "note")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idnote")
    private long idNote;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "date")
    private String dateTime;

    @ManyToOne
    @JoinColumn(name = "idauthor", nullable = false)
    private Author author;

    @Transient
    private long authorID;

    public long getAuthorID() {
        return authorID;
    }

    public void setAuthorID(long authorID) {
        this.authorID = authorID;
    }

    public long getIdNote() {
        return idNote;
    }

    public void setIdNote(long idNote) {
        this.idNote = idNote;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
