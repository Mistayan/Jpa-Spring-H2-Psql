package fr.epsi.rennes.cours.uml.umlspringjpa.dao;

import fr.epsi.rennes.cours.uml.umlspringjpa.exception.DaoException;
import fr.epsi.rennes.cours.uml.umlspringjpa.model.Contact;

public interface ContactDao {
    /**
     * Get a contact by id
     */
    Contact getById(Integer id) throws DaoException;

    /**
     * Get a contact by name
     */
    Contact getByName(String name) throws DaoException;

    /**
     * Get a contact by email
     */
    Contact getByEmail(String email) throws DaoException;

    /**
     * Get a contact by phone
     */
    Contact getByPhone(String phone) throws DaoException;

    /**
     * Get a contact by address
     */
    Contact getByAddress(String address) throws DaoException;

    void createContact(Contact contact) throws DaoException;
}
