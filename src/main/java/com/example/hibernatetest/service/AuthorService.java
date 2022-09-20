package com.example.hibernatetest.service;

import com.example.hibernatetest.entity.Author;
import com.example.hibernatetest.reposiroty.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    /*public void update(Author author){
        repository.update(author);
    }*/

    public void delete(long id){
        repository.delete(id);
    }
}
