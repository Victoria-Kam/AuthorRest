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


    @GetMapping("/")
    public List<Author> getAuthors(){
        return  service.getAll();
    }

    @GetMapping("/{id}")
    public Author getAuthor(@PathVariable long id){
        return service.getOne(id);
    }
    /**
     * тут передаю ID автора, которого буду выводить.
     * После передачи ID в сессии ищу объект(автора), у которого есть этот ID:
     * session.get(Author.class, id);
     * */


    @PostMapping("/save")
    public String saveAuthor(@RequestBody Author author){
        service.save(author);
        return "save!";
    }
    /**
     * тут передаю самого автора. Далее просто в автора сохраняю через сессию
     * session.save(author);
     * */


    @PutMapping("/change/{id}")
    public String changeAuthor(@PathVariable long id, @RequestBody Author author){
        service.update(author,id);
        return "change!";
    }
    /**
     * тут передаю как самого автора, так и его ID. Далее просто в автора добавляю его ID и обновляю.
     * author.setIdAuthor(id);
     * */


    @DeleteMapping("/{id}")
    public String deleteAuthor(@PathVariable long id){
        service.delete(id);
        return "deleted";
    }
    /**
     * тут передаю ID автора, которого буду удалять.
     * После передачи ID в сессии ищу объект(автора), у которого есть этот ID:
     * Author author = session.find(Author.class, id);
     * */


}
