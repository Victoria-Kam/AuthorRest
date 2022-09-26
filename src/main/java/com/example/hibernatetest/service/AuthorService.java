package com.example.hibernatetest.service;

import com.example.hibernatetest.entity.Author;
import com.example.hibernatetest.entity.Note;
import com.example.hibernatetest.reposiroty.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class AuthorService {

    private final AuthorRepository repository;

    @Autowired
    public AuthorService(AuthorRepository repository) {
        this.repository = repository;
    }

    public List<Author> getAll(){
        return repository.getAll();
    }

    public Author getOne(long id){
        return repository.getOne(id);
    }

    public void save(Author author){
        repository.save(author);
    }

    public void update(Author author, long id){
        repository.update(author,id);
    }

    public void delete(long id){
        repository.delete(id);
    }

    public List<Note> getNotes(long id){
        return repository.getNotes(id);
    }
}
