import dao.HibernateSessionFactory;
import dao.entities.Person;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Main {
    public static void main(String[] args) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(new Person("name"));
        transaction.commit();
        session.close();
        System.out.println("Hello");
    }
}
