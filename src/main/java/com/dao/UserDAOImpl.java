package com.dao;

import com.model.User;
import org.springframework.stereotype.Repository;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO{

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("from User", User.class).getResultList();
    }

    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
    }


    @Override
    public User getUserById(long id) {
         return entityManager.find(User.class, id);
    }

    @Override
    public void updateUser(User updateUser, long id) {
        entityManager.merge(updateUser);
   }

    @Override
    public void deleteUser(long id) {
        entityManager.remove(entityManager.find(User.class, id));
    }

    @Override
    public User getUserByName(String username) {
        return (User) entityManager.createQuery("from User where username = :username")
                .setParameter("username", username)
                .getSingleResult();
    }

}
