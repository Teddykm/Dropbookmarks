package com.udemy.dropbookmarks.core;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "bookmarks")
@NamedQueries({
        @NamedQuery(name = "com.udemy.dropbookmarks.core.findForUser",
                    query = "select b from Bookmark b "
                    + "where b.user.id = :id"),
        @NamedQuery(name = "com.udemy.dropbookmarks.core.remove",
                    query =  "delete from Bookmark b "
                    + "where b.id = :id")
})
public class Bookmark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String url;
    private String descriptions;
    @JsonIgnore
    @ManyToOne
    private User user;

    private Bookmark(){}

    public Bookmark(String name, String url, String descriptions, User owner) {
        this.name = name;
        this.url = url;
        this.descriptions = descriptions;
        this.user = owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bookmark)) return false;

        Bookmark bookmark = (Bookmark) o;

        if (id != bookmark.id) return false;
        if (name != null ? !name.equals(bookmark.name) : bookmark.name != null) return false;
        if (url != null ? !url.equals(bookmark.url) : bookmark.url != null) return false;
        if (descriptions != null ? !descriptions.equals(bookmark.descriptions) : bookmark.descriptions != null)
            return false;
        return !(user != null ? !user.equals(bookmark.user) : bookmark.user != null);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (descriptions != null ? descriptions.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
