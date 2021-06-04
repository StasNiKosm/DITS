import dao.DaoPerson;
import dao.HibernateSessionFactory;
import dao.entities.Person;
import org.hibernate.Session;
import org.junit.Test;
import scriptDB.DBInitializer;

import java.sql.SQLException;

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

    @Test
    public void initTest() {
        try {
            DBInitializer.init();
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

}
