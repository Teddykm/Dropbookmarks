package com.udemy.dropbookmarks.db;

import com.google.common.base.Optional;
import com.udemy.dropbookmarks.core.User;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;



public class UserDAO extends AbstractDAO<User>{

    public UserDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<User> findAll () {
        return list(namedQuery("com.udemy.dropbookmarks.core.User.findAll"));
    }

    public Optional<User> findByUserPassword(String username, String password) {
        return Optional.fromNullable(uniqueResult(namedQuery("com.udemy.dropbookmarks.core.User.findByUsernamePassword")
                .setParameter("username", username)
                .setParameter("password", password)));

    }
}
