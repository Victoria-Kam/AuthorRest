package com.example.hibernatetest.reposiroty;

import com.example.hibernatetest.entity.Author;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
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

    public void save(Author author) {
        Session session = sessionFactory.getCurrentSession();
        session.save(author); // вот так вот можно сохранить в БД
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

}
