package com.example.hibernatetest.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idauthor")
    private long idAuthor;

    @Column(name = "nickname", nullable = false)
    private String nickName;

    @Column(name = "age", nullable = false)
    private int age;

    @Column(name = "email", nullable = false)
    private String email;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
   // @JoinColumn(name = "idauthor")
    private Set<Note> notes;


    public Set<Note> getNotes() {
        return notes;
    }

    public void setNotes(Set<Note> notes) {
        this.notes = notes;
    }

    public long getIdAuthor() {
        return idAuthor;
    }

    public void setIdAuthor(long idAuthor) {
        this.idAuthor = idAuthor;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
