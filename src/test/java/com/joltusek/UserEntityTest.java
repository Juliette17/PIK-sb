package com.joltusek;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by grzesiek on 15.04.17.
 */
public class UserEntityTest {
    private SessionFactory ourSessionFactory;
    private Session session = null;
    private UserEntity user, anotherUser;

    /* create two identical users
     * before each test */
    @Before
    public void before() {
        user = new UserEntity();
        user.setId(99999);
        user.setLogin("johnsmith99");
        user.setPasshash("smith99");

        anotherUser = new UserEntity();
        anotherUser.setId(user.getId());
        anotherUser.setLogin(user.getLogin());
        anotherUser.setPasshash(user.getPasshash());
    }

    @Test
    public void addNewUserTest() {
        UserEntity newUser;

        openSession();
        session.save(user);
        newUser = (UserEntity) session.get(UserEntity.class, 99999);
        closeSession();

        assertNotNull(newUser);
        assertEquals(newUser.getId(), user.getId());
        assertEquals(newUser.getLogin(), user.getLogin());
        assertEquals(newUser.getPasshash(), user.getPasshash());
    }

    @Test
    public void equalsMethodWithNullArgumentTest() {
        assertFalse(user.equals(null));
    }

    @Test
    public void equalsMethodDifferentIDTest() {
        anotherUser.setId(user.getId()+1);

        assertFalse(user.equals(anotherUser));
    }

    @Test
    public void equalsMethodDifferentLoginTest() {
        anotherUser.setLogin(user.getLogin()+'a');

        assertFalse(user.equals(anotherUser));
    }

    @Test
    public void equalsMethodDifferentPasshashTest() {
        anotherUser.setPasshash(user.getPasshash()+'a');

        assertFalse(user.equals(anotherUser));
    }

    private void openSession() {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();

            ourSessionFactory = configuration.buildSessionFactory();
            session = ourSessionFactory.openSession();
        } catch (Throwable ex) {
            fail();
        }
    }

    private void closeSession() {
        session.close();
        ourSessionFactory.close();
    }
}
