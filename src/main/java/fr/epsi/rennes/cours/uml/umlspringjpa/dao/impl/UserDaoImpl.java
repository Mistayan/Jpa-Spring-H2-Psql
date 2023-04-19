package fr.epsi.rennes.cours.uml.umlspringjpa.dao.impl;

import fr.epsi.rennes.cours.uml.umlspringjpa.dao.UserDao;
import fr.epsi.rennes.cours.uml.umlspringjpa.exception.DaoException;
import fr.epsi.rennes.cours.uml.umlspringjpa.model.MyUser;
import jakarta.persistence.EntityManager;

import java.util.List;

public class UserDaoImpl implements UserDao {

    private final EntityManager em;

    public UserDaoImpl(EntityManager entityManager) {
        this.em = entityManager;
    }


    /**
     * Get a user by id
     *
     * @param id the id
     * @return the user
     * @throws DaoException
     */
    @Override
    public MyUser getById(Integer id) throws DaoException {
        return null;
    }

    /**
     * Get a user by username
     *
     * @param username the username
     * @return the user
     * @throws DaoException
     */
    @Override
    public MyUser getByUsername(String username) throws DaoException {
        return null;
    }

    /**
     * Get a list of all users
     *
     * @return the list of all users
     * @throws DaoException
     */
    @Override
    public List<MyUser> getAll() throws DaoException {
        return null;
    }

    /**
     * Get a user by username and password
     *
     * @param login
     * @param password the password
     * @return MyUser
     * @throws DaoException
     */
    @Override
    public MyUser getByUsernameAndPassword(String login, String password) throws DaoException {
        return null;
    }

    /**
     * Create a new myUser
     *
     * @param myUser the myUser to create
     * @throws DaoException
     */
    @Override
    public void createUser(MyUser myUser) throws DaoException {

    }

    /**
     * Update a myUser
     *
     * @param myUser the myUser to update
     * @throws DaoException
     */
    @Override
    public void updateUser(MyUser myUser) throws DaoException {

    }

    /**
     * Delete a myUser
     *
     * @param myUser the myUser to delete
     * @throws DaoException
     */
    @Override
    public void deleteUser(MyUser myUser) throws DaoException {

    }
}
