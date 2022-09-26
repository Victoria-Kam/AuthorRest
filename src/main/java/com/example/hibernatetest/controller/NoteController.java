package com.example.hibernatetest.controller;

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

    @GetMapping("/{id}")
    public Note getOne(@PathVariable long id){
        return noteService.getOne(id);
    }

    @PostMapping("/add-note")
    public String saveNote(@RequestBody Note note){
        noteService.addNote(note);
        return "save!";
    }

    @PutMapping("/change-note/{id}")
    public String changeNote(@PathVariable long id, @RequestBody Note note){
        noteService.update(id,note);
        return "change!";
    }

    @DeleteMapping("/delete-note/{id}")
    public String deleteNote(@PathVariable long id){
        noteService.delete(id);
        return "deleted!";
    }
}
