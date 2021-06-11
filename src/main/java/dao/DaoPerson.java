package dao;

import dao.entities.Person;

public class DaoPerson extends AbstractDao<Person> {

    public Person findByID(int id) {
        return HibernateSessionFactory.getSessionFactory().openSession().get(Person.class, id);
    }

    public void update(Person person) {
        commitOperation(session -> session.update(person));
    }

    public void save(Person person) {
        commitOperation(session -> session.save(person));
    }

}
