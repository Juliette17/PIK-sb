package com.joltusek;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Before;
import org.junit.Test;
import com.joltusek.PointEntity;
import com.joltusek.UserEntity;

import java.io.Serializable;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

/**
 * Created by grzesiek on 15.04.17.
 */
public class PointEntityTest {
    private SessionFactory ourSessionFactory;
    private Session session = null;
    private PointEntity point;
    private PointEntity anotherPoint;
    private UserEntity user;

    /* create simple user and two identical points
    *  before each test */
    @Before
    public void before(){
        point = new PointEntity();
        anotherPoint = new PointEntity();

        user = new UserEntity();
        user.setId(99999);
        user.setLogin("johnsmith99");
        user.setPasshash("smith99");

        point.setId(point.hashCode());
        point.setLatitude(51);
        point.setLongitude(23);
        point.setName("simplename");
        point.setUserByOwner(user);

        anotherPoint.setId(point.getId());
        anotherPoint.setLatitude(point.getLatitude());
        anotherPoint.setLongitude(point.getLongitude());
        anotherPoint.setName(point.getName());
        anotherPoint.setUserByOwner(point.getUserByOwner());

    }

    @Test
    public void addNewPointTest() {
        PointEntity newPoint;

        openSession();
        session.save(user);
        Serializable save = session.save(point);
        newPoint = (PointEntity) session.get(PointEntity.class, point.getId());
        closeSession();

        assertNotNull(newPoint);
        assertEquals(newPoint.getId(), point.getId());
        assertEquals(newPoint.getLatitude(), point.getLatitude(), 0.1);
        assertEquals(newPoint.getLongitude(), point.getLongitude(), 0.1);
        assertEquals(newPoint.getName(), point.getName());
        assertEquals(newPoint.getUserByOwner(), point.getUserByOwner());
    }

    @Test
    public void equalsMethodWithNullArgumentTest() {
        assertFalse(point.equals(null));
    }

    @Test
    public void equalsMethodDifferentIDTest() {
        point.setId(point.getId()+1);

        assertFalse(point.equals(anotherPoint));
    }

    @Test
    public void equalsMethodDifferentLatitudeTest() {
        point.setLatitude(point.getLatitude()+1);

        assertFalse(point.equals(anotherPoint));
    }

    @Test
    public void equalsMethodDifferentLongitudeTest() {
        point.setLongitude(point.getLongitude()+1);

        assertFalse(point.equals(anotherPoint));
    }

    @Test
    public void equalsMethodDifferentNameTest() {
        point.setName(point.getName() + "A");

        assertFalse(point.equals(anotherPoint));
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
