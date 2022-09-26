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

        //String sql = "select * from note";
        Session session = sessionFactory.getCurrentSession();   // открываем сессию
        TypedQuery<Note> query = session.createNativeQuery(sql);
        return query.getResultList();
    }


   public void addNote(Note note) {
        Session session = sessionFactory.getCurrentSession();   // открываем сессию
        note.setAuthor(findAuthor(note.getAuthorID()));
        session.save(note);
    }


    public Note getOne(long id){
        Session session = sessionFactory.getCurrentSession();
        return session.get(Note.class, id);
    }


    public void update(long id, Note note){
        Session session = sessionFactory.getCurrentSession();
        note.setIdNote(id);
        session.update(note);
    }

    public void delete(long id){
        Session session = sessionFactory.getCurrentSession();
        Note note = session.find(Note.class,id);
        session.delete(note);
    }





    private Author findAuthor(long id) {

       /* String sql = "select * from author where nickname = '" + authorName + "'";
        Session session = sessionFactory.getCurrentSession();   // открываем сессию
        TypedQuery<Author> query = session.createNativeQuery(sql);*/
        Session session = sessionFactory.getCurrentSession();
        return session.find(Author.class,id);
    }
}
