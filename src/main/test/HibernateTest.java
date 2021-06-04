import dao.DaoPerson;
import dao.HibernateSessionFactory;
import dao.entities.Person;
import org.hibernate.Session;
import org.junit.Test;

public class HibernateTest {

    @Test
    public void checkConnection() {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.close();
    }

    @Test
    public void checkSave() {
        DaoPerson daoPerson = new DaoPerson();
        daoPerson.save(new Person("Dave"));
        daoPerson.save(new Person("Vlad"));
    }

    @Test
    public void checkSelect() {
        DaoPerson daoPerson = new DaoPerson();
        Person person = new Person("new person");
        daoPerson.save(person);
        person = daoPerson.findByID(person.getId());
    }

}
