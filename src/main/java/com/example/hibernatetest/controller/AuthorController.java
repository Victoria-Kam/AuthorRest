package com.example.hibernatetest.controller;

import com.example.hibernatetest.entity.Author;
import com.example.hibernatetest.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {

    private final AuthorService service;


    @Autowired
    public AuthorController(AuthorService service) {
        this.service = service;
    }

    @PostMapping("/save")
    public String saveAuthor(@RequestBody Author author){
        service.save(author);

        return "save!";
    }

    @GetMapping("/")
    public List<Author> getAuthors(){
        return  service.getAll();
    }

    @GetMapping("/{id}")
    public Author getAuthor(@PathVariable long id){
        return service.getOne(id);
    }


    @DeleteMapping("/{id}")
    public String deleteAuthor(@PathVariable long id){
        service.delete(id);

        return "deleted";
    }


}
