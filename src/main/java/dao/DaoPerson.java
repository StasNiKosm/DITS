package dao;

import dao.entities.Person;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.function.Consumer;

public class DaoPerson {

    public Person findByID(int id) {
        return HibernateSessionFactory.getSessionFactory().openSession().get(Person.class, id);
    }

    private static void commitOperation(Consumer<Session> operation) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        operation.accept(session);
        transaction.commit();
        session.close();
    }

    public void update(Person person) {
        commitOperation(session -> session.update(person));
    }

    public void save(Person person) {
        commitOperation(session -> session.save(person));
    }

}
