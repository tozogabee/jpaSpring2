package com.jpa.repository;

import com.jpa.repository.model.Book;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class BookRepositoryEM {

    @PersistenceContext
    private EntityManager entityManager;

    public Book save(Book book) {
        entityManager.persist(book);
        return book;
    }

    public Book findById(long id) {
        return entityManager.find(Book.class, id);
    }

    public Book update(Book book) {
        entityManager.merge(book);
        return book;
    }

    public List<Book> findByTitle(String title) {
        return entityManager
                .createNamedQuery("findByTitle", Book.class)
                .setParameter("title", title)
                .setMaxResults(10)
                .getResultList();
    }
}
