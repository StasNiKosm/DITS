import dao.HibernateSessionFactory;
import org.hibernate.Session;

public class Main {
    public static void main(String[] args) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.createSQLQuery("create table test_1(" +
                "id int" +
                ");");
        session.close();
    }
}
