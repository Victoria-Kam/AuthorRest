package com.example.hibernatetest.controller;

import com.example.hibernatetest.entity.Author;
import com.example.hibernatetest.entity.Note;
import com.example.hibernatetest.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/note")
public class NoteController {


    private NoteService noteService;

    @Autowired
    public NoteController(NoteService service) {
        this.noteService = service;
    }

    @GetMapping("/")
    public List<Note> getAll(){

        return noteService.getAll();
    }

    @PostMapping("/add-note")
    public String saveAuthor(@RequestBody Note note){
        noteService.addNote(note);

        return "save!";
    }

    @GetMapping("/{id}")
    public Note getOne(@PathVariable long id){
        return noteService.getOne(id);
    }
}
