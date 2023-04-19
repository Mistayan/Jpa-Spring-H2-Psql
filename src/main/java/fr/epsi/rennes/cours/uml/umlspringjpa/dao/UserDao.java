package fr.epsi.rennes.cours.uml.umlspringjpa.dao;

import fr.epsi.rennes.cours.uml.umlspringjpa.exception.DaoException;
import fr.epsi.rennes.cours.uml.umlspringjpa.model.MyUser;

import java.util.List;

public interface UserDao {

    /**
     * Get a user by id
     *
     * @param id the id
     * @return the user
     * @throws DaoException
     */
    MyUser getById(Integer id) throws DaoException;

    /**
     * Get a user by username
     *
     * @param username the username
     * @return the user
     * @throws DaoException
     */
    MyUser getByUsername(String username) throws DaoException;

    /**
     * Get a list of all users
     *
     * @return the list of all users
     * @throws DaoException
     */
    List<MyUser> getAll() throws DaoException;

    /**
     * Get a user by username and password
     *
     * @param username the username
     * @param password the password
     * @return MyUser
     * @throws DaoException
     */
    MyUser getByUsernameAndPassword(String login, String password) throws DaoException;

    /**
     * Create a new myUser
     *
     * @param myUser the myUser to create
     * @throws DaoException
     */
    void createUser(MyUser myUser) throws DaoException;

    /**
     * Update a myUser
     *
     * @param myUser the myUser to update
     * @throws DaoException
     */
    void updateUser(MyUser myUser) throws DaoException;

    /**
     * Delete a myUser
     *
     * @param myUser the myUser to delete
     * @throws DaoException
     */
    void deleteUser(MyUser myUser) throws DaoException;

}
