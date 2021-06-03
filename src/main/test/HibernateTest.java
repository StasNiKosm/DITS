import dao.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class HibernateTest {

    @Test
    public void checkConnection() {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.close();
    }

    @Test
    public void checkSave() {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        checkSave(session);
        transaction.commit();
        session.close();
    }

    public void checkSave(Session session) {
        session.save(new dao.entities.Test("name"));
    }


    @Test
    public void checkSelect() {

        Session session = HibernateSessionFactory.getSessionFactory().openSession();

        Transaction transaction = session.beginTransaction();

        checkSave(session);

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<dao.entities.Test> testCriteriaQuery = criteriaBuilder.createQuery(dao.entities.Test.class);

        Root<dao.entities.Test> root = testCriteriaQuery.from(dao.entities.Test.class);
        testCriteriaQuery.select(root);

        TypedQuery<dao.entities.Test> typedQuery = session.createQuery(testCriteriaQuery);
        List<dao.entities.Test> list = typedQuery.getResultList();

        list.forEach(System.out::println);

        assert list.size() > 0;

        transaction.commit();

        session.close();

    }

}
