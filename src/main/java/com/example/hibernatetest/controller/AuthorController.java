package com.example.hibernatetest.controller;

import com.example.hibernatetest.entity.Author;
import com.example.hibernatetest.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/author")
public class AuthorController {

    private final AuthorService service;


    @Autowired
    public AuthorController(AuthorService service) {
        this.service = service;
    }


    @GetMapping("/")
    public List<Author> getAuthors() {
        return service.getAll();
    }

   @GetMapping("/{id}")
    public Optional<Author> getAuthor(@PathVariable long id) {
        return service.getOne(id);
    }

    /**
     * тут передаю ID автора, которого буду выводить.
     * После передачи ID в сессии ищу объект(автора), у которого есть этот ID:
     * session.get(Author.class, id);
     */

   /* @GetMapping("/notes/{id}")
    public List<Note> getNotes(@PathVariable long id){
        return service.getNotes(id);
    }*/

    @PostMapping("/save")
    @PreAuthorize("hasAnyRole('ROLE_AUTHOR', 'ROLE_ADMIN')")
    public String saveAuthor(@RequestBody Author author) {
        service.save(author);
        return "save!";
    }


    @PutMapping("/change")
    @PreAuthorize("hasAnyRole('ROLE_AUTHOR', 'ROLE_ADMIN')")
    public ResponseEntity<String> changeAuthor(@PathVariable long id, @RequestBody Author author) {
        Optional<Author> optionalAuthor = service.getOne(id);
        if(optionalAuthor.isEmpty()){
            return new ResponseEntity<>("Author not found", HttpStatus.NOT_FOUND);
        }
        Author dbArtist = optionalAuthor.get();
        dbArtist.setNickName(dbArtist.getNickName());
        service.update(dbArtist);
        return new ResponseEntity<>("Author was updated", HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> deleteAuthor(@PathVariable long id) {
        Optional<Author> optionalAuthor = service.getOne(id);
        if(optionalAuthor.isEmpty()){
            return new ResponseEntity<>("Author not found", HttpStatus.NOT_FOUND);
        }
        Author dbArtist = optionalAuthor.get();
        service.delete(dbArtist);
        return new ResponseEntity<>("Author was deleted", HttpStatus.NO_CONTENT);
    }


}
