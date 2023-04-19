package fr.epsi.rennes.cours.uml.umlspringjpa.dao.impl;

import fr.epsi.rennes.cours.uml.umlspringjpa.dao.AgendaDao;
import fr.epsi.rennes.cours.uml.umlspringjpa.exception.DaoException;
import fr.epsi.rennes.cours.uml.umlspringjpa.model.Agenda;
import fr.epsi.rennes.cours.uml.umlspringjpa.model.MyUser;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.List;

public class AgendaDaoImpl implements AgendaDao {

    private final EntityManager em;

    public AgendaDaoImpl(EntityManager entityManager) {
        this.em = entityManager;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Agenda getById(Integer id) throws DaoException {
        try {
            return em.find(Agenda.class, id);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    /**
     * {@inheritDoc}
     * Utilisation d'une TypedQuery
     */
    @Override
    public List<Agenda> getAllAgendas() throws DaoException {
        //TODO
        try {
            return em.createQuery("SELECT o FROM Agenda o", Agenda.class).getResultList();
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    /**
     * Get a list of agendas for a type and a status
     *
     * @param user the target user
     * @return a list of agendas
     * @throws DaoException
     */
    @Override
    public Agenda getAgendaByMyUser(MyUser user) throws DaoException {
        try {
            Query query = em.createQuery("SELECT o FROM Agenda o WHERE o.myUser = :user");
            query.setParameter("user", user);
            return (Agenda) query.getSingleResult();
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    /**
     * Get a list of agendas for a number of days greater than the value of parameter
     *
     * @return a list of agendas with contacts
     * @throws DaoException
     */
    @Override
    public List<Agenda> getAgendasWithContacts() throws DaoException {
        try {
            Query query = em.createQuery("SELECT o FROM Agenda o WHERE o.contacts IS NOT EMPTY");
            return query.getResultList();
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    /**
     * Create an agenda
     *
     * @param agenda the agenda to create
     * @throws DaoException
     */
    @Override
    public void createAgenda(Agenda agenda) throws DaoException {
        try {
            em.persist(em.merge(agenda));
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    /**
     * Update an agenda
     *
     * @param agenda the agenda to update
     * @throws DaoException
     */
    @Override
    public void updateAgenda(Agenda agenda) throws DaoException {
        try {

            em.persist(em.merge(agenda));
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteAgenda(Agenda agenda) throws DaoException {
        Query query = em.createQuery("SELECT o FROM Agenda o WHERE o.id = :agendaId");
        query.setParameter("agendaId", agenda.getId());
        Agenda agendaToDelete = (Agenda) query.getSingleResult();
        em.remove(em.merge(agendaToDelete));
    }

    @Override
    public List<Agenda> getAll() {
        return em.createQuery("SELECT o FROM Agenda o", Agenda.class).getResultList();
    }
}
