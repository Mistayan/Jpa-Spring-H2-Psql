package fr.epsi.rennes.cours.uml.umlspringjpa.dao;

import fr.epsi.rennes.cours.uml.umlspringjpa.exception.DaoException;
import fr.epsi.rennes.cours.uml.umlspringjpa.model.Agenda;
import fr.epsi.rennes.cours.uml.umlspringjpa.model.MyUser;

import java.util.List;

public interface AgendaDao {

    /**
     * Get an agenda by id
     *
     * @param id the agenda id
     * @return the agenda
     * @throws DaoException
     */
    Agenda getById(Integer id) throws DaoException;

    /**
     * Get all agendas
     *
     * @return a list of agendas
     * @throws DaoException
     */
    List<Agenda> getAllAgendas() throws DaoException;

    /**
     * Get a list of agendas for a type and a status
     *
     * @param user the target user
     * @return a list of agendas
     * @throws DaoException
     */
    Agenda getAgendaByMyUser(MyUser user) throws DaoException;

    /**
     * Get a list of agendas for a number of days greater than the value of parameter
     *
     * @return a list of agendas with contacts
     * @throws DaoException
     */
    List<Agenda> getAgendasWithContacts() throws DaoException;

    /**
     * Create an agenda
     *
     * @param agenda the agenda to create
     * @throws DaoException
     */
    void createAgenda(Agenda agenda) throws DaoException;

    /**
     * Update an agenda
     *
     * @param agenda the agenda to update
     * @throws DaoException
     */
    void updateAgenda(Agenda agenda) throws DaoException;

    /**
     * Delete an agenda
     *
     * @param agenda the agenda to delete
     * @throws DaoException
     */
    void deleteAgenda(Agenda agenda) throws DaoException;

    /**
     * getall users
     */
    List<Agenda> getAll() throws DaoException;
}
