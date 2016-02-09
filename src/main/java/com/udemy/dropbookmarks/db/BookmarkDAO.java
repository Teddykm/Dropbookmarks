package com.udemy.dropbookmarks.db;

import com.google.common.base.Optional;
import com.udemy.dropbookmarks.core.Bookmark;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;



public class BookmarkDAO extends AbstractDAO<Bookmark> {

    public BookmarkDAO(SessionFactory sessionFactory) {super(sessionFactory); }

    public List<Bookmark> findForUser(long id) {
        return list(namedQuery("com.udemy.dropbookmarks.core.findForUser")
                .setParameter("id", id));
    }

    public Optional<Bookmark> findById(long id) {
        return Optional.fromNullable(get(id));
    }

    public Bookmark save(Bookmark bookmark) {
        return persist(bookmark);
    }

    public void delete(Bookmark bookmark) {
        namedQuery("com.udemy.dropbookmarks.core.remove")
                .setParameter("id", bookmark.getId())
                .executeUpdate();
    }

}
