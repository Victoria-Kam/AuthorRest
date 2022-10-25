package com.example.hibernatetest.reposiroty;

import com.example.hibernatetest.entity.Author;
import com.example.hibernatetest.entity.Note;
import com.example.hibernatetest.reposiroty.Interface.IAuthorRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import java.util.List;

@Repository
@Transactional
public class AuthorRepository {


    private final SessionFactory sessionFactory;

    public AuthorRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Author> getAll() {

        String sql = "select * from author";
        Session session = sessionFactory.getCurrentSession();   // открываем сессию
        TypedQuery<Author> query = session.createNativeQuery(sql);
        return query.getResultList();
    }

    public Author save(Author author) {
        Session session = sessionFactory.getCurrentSession();
        session.save(author); // вот так вот можно сохранить в БД
        return author;
    }


    public Author getOne(long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Author.class, id);
    }


    public void update(Author author, long id) {
        Session session = sessionFactory.getCurrentSession();
        author.setIdAuthor(id);
        session.update(author);
    }

    public void delete(long id) {

        Session session = sessionFactory.getCurrentSession();
        Author author = session.find(Author.class, id);
        session.delete(author);
    }


    public List<Note> getNotes(long id) {
        String sql = "select note.title, note.content, note.date , author.nickname from note inner join author on note.idauthor = author.idauthor where author.idauthor = " + id + ";";
        Session session = sessionFactory.getCurrentSession();   // открываем сессию
        TypedQuery<Note> query = session.createNativeQuery(sql);
        return query.getResultList();
    }

}
