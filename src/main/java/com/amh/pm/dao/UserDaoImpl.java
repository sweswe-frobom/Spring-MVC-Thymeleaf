package com.amh.pm.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.amh.pm.entity.User;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    User user = null;

    @Override
    public void save(User user) {
        entityManager.persist(user);
    }

    @Override
    public List<User> findAll() {
        return entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    @Override
    public User findById(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public List<User> findUserNameByOrgnId(int orgId) {

        Query q = entityManager.createQuery("select u from User u JOIN u.orgList orgmlist WHERE orgmlist.id=?");

        q.setParameter(1, orgId);
        List<User> userNames = q.getResultList();
        return userNames;
    }

    public User findUserIdByName(String userName) {

        User user = null;
        try {
            Query q = entityManager.createQuery("select u from User u WHERE u.name=?");
            q.setParameter(1, userName);
            user = (User) q.getSingleResult();
        } catch (NoResultException e) {
            System.out.println("Error is :" + e);
        }

        return user;
    }

    @Override
    public void delete(User user) {

        entityManager.remove(user);
        user = entityManager.find(User.class, 1);
        System.out.println("User after removal :- " + user);
    }

    @Override
    public void update(User user) {

        user.setName("Hla Hla");
        System.out.println("User name after updation :- " + user);
    }

    @Override

    public User userByName(String name, String password) {
        try {
            Query q = entityManager.createQuery("SELECT u FROM User u WHERE u.name=? AND u.password=?");

            q.setParameter(1, name);

            q.setParameter(2, password);

            user = (User) q.getSingleResult();

        } catch (NoResultException e) {
            System.out.println(e);
            // TODO: handle exception
        }
        return user;
    }

    @Override
    public User findUserByEmail(String userEmail) {
        try {
            Query q = entityManager.createQuery("SELECT u FROM User u WHERE u.email=?");

            q.setParameter(1, userEmail);

            user = (User) q.getSingleResult();

        } catch (NoResultException e) {
            System.out.println(e);
        }
        return user;
    }
}
