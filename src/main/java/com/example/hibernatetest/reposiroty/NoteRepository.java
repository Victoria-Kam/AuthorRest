package com.example.hibernatetest.reposiroty;

import com.example.hibernatetest.entity.Author;
import com.example.hibernatetest.entity.Note;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class NoteRepository {

    private final SessionFactory sessionFactory;

    public NoteRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Note> getAll() {

        String sql = "select note.title, note.content, note.date , author.nickname from note inner join author on note.idauthor = author.idauthor";
        Session session = sessionFactory.getCurrentSession();   // открываем сессию
        TypedQuery<Note> query = session.createNativeQuery(sql);
        return query.getResultList();
    }


    public void addNote(Note note) {
        Session session = sessionFactory.getCurrentSession();   // открываем сессию
        note.setAuthor(findAuthor(note.getAuthorName()));
        session.save(note);
    }


    private List<Author> findAuthor(String authorName) {

        String sql = "select * from author where nickname = '" + authorName + "'";
        Session session = sessionFactory.getCurrentSession();   // открываем сессию
        TypedQuery<Author> query = session.createNativeQuery(sql);
        return query.getResultList();
    }

    public Note getOne(long id){
        Session session = sessionFactory.getCurrentSession();
        return session.get(Note.class, id);
    }

}
