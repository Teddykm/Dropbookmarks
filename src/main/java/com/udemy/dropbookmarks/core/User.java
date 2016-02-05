package com.udemy.dropbookmarks.core;

import javax.persistence.*;

@Entity
@Table(name = "users")
@NamedQueries({
        @NamedQuery(name = "com.udemy.dropbookmarks.core.User.findAll",
                query = "select u from User u"),
        @NamedQuery(name = "com.udemy.dropbookmarks.core.User.findByUsernamePassword",
                query = "select u from User u"
                        + "where u.username = :username"
                        + "where u.password = :password")
})

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;

    private String username;

    private String password;

    public User(){}

    public User (String username, String password){
        this.username = username;
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (username != null ? !username.equals(user.username) : user.username != null) return false;
        return !(password != null ? !password.equals(user.password) : user.password != null);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
