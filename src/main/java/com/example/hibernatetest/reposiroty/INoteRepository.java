package com.example.hibernatetest.reposiroty;

import com.example.hibernatetest.entity.Author;
import com.example.hibernatetest.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface INoteRepository  extends JpaRepository<Note,Integer> {

   // List<Note> getAll();

    //void addNote(Note note);

   // Note getOne(long id);

    //void update(long id, Note note);
   // void delete(long id);

}
