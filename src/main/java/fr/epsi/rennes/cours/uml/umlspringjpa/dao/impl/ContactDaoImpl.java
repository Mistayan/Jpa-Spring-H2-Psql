package fr.epsi.rennes.cours.uml.umlspringjpa.dao.impl;

import fr.epsi.rennes.cours.uml.umlspringjpa.dao.ContactDao;
import fr.epsi.rennes.cours.uml.umlspringjpa.exception.DaoException;
import fr.epsi.rennes.cours.uml.umlspringjpa.model.Contact;
import jakarta.persistence.EntityManager;

public class ContactDaoImpl implements ContactDao {
    private final EntityManager em;

    public ContactDaoImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * Get a contact by id
     *
     * @param id
     */
    @Override
    public Contact getById(Integer id) throws DaoException {
        try {
            return em.find(Contact.class, id);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    /**
     * Get a contact by name
     *
     * @param name
     */
    @Override
    public Contact getByName(String name) throws DaoException {
        try {
            return em.createQuery("SELECT c FROM Contact c WHERE c.cName = :name", Contact.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    /**
     * Get a contact by email
     *
     * @param email
     */
    @Override
    public Contact getByEmail(String email) throws DaoException {
        try {
            return em.createQuery("SELECT c FROM Contact c WHERE c.email = :email", Contact.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    /**
     * Get a contact by phone
     *
     * @param phone
     */
    @Override
    public Contact getByPhone(String phone) throws DaoException {
        try {
            return em.createQuery("SELECT c FROM Contact c WHERE c.phones = :phone", Contact.class)
                    .setParameter("phone", phone)
                    .getSingleResult();
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    /**
     * Get a contact by address
     *
     * @param address
     */
    @Override
    public Contact getByAddress(String address) throws DaoException {
        try {
            return em.createQuery("SELECT c FROM Contact c WHERE c.address = :address", Contact.class)
                    .setParameter("address", address)
                    .getSingleResult();
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void createContact(Contact contact) throws DaoException {
        try {
            em.persist(em.merge(contact));
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }
}
