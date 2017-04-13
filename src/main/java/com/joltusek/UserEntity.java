package com.joltusek;

import javax.persistence.*;

/**
 * Created by Julita Ołtusek on 13.04.2017.
 */
@Entity
@Table(name = "user", schema = "pik", catalog = "postgres")
public class UserEntity {
    private int id;
    private String login;
    private String passhash;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "login", nullable = false, length = -1)
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Basic
    @Column(name = "passhash", nullable = false, length = -1)
    public String getPasshash() {
        return passhash;
    }

    public void setPasshash(String passhash) {
        this.passhash = passhash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (id != that.id) return false;
        if (login != null ? !login.equals(that.login) : that.login != null) return false;
        if (passhash != null ? !passhash.equals(that.passhash) : that.passhash != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (passhash != null ? passhash.hashCode() : 0);
        return result;
    }
}
