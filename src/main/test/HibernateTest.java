import dao.DaoPerson;
import dao.DaoTopic;
import dao.HibernateSessionFactory;
import dao.entities.Person;
import dao.entities.Question;
import dao.entities.Topic;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import provider.AppContextProvider;
import scriptDB.DBInitializer;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.function.Consumer;

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

    public static void safetyOperation(Consumer<Session> operation) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            operation.accept(session);
            transaction.commit();
            session.close();
        } catch (Exception ex) {
            transaction.rollback();
            throw new RuntimeException(ex);
        }
    }

    public static void printTopic(Topic topic) {
        topic.getTests().forEach(test -> {
            System.out.println("### " + test + " | topic_id=" + test.getTopic().getTopicId());
            test.getQuestions().forEach(question -> {
                System.out.println("### " + question + " | test_id=" + question.getTest().getTestId());
            });
        });
    }

    @Test
    public void relTest() {
        Topic topic = new Topic(0, "topic", "topic-1");
        safetyOperation((session -> session.save(topic)));

        dao.entities.Test test_1 = new dao.entities.Test(0, "test-description-1", "test-1", topic);
        safetyOperation((session -> session.save(test_1)));

        dao.entities.Test test_2 = new dao.entities.Test(0, "test-description-2", "test-2", topic);
        safetyOperation((session -> session.save(test_2)));

        dao.entities.Question question_1 = new Question(0, "question-1", test_1);
        dao.entities.Question question_2 = new Question(0, "question-2", test_1);
        test_1.setQuestions(Arrays.asList(question_1, question_2));
        test_1.getQuestions().forEach(question -> safetyOperation(session -> session.save(question)));

        dao.entities.Question question_3 = new Question(0, "question-3", test_2);
        safetyOperation((session -> session.save(question_3)));

        Session session = HibernateSessionFactory.getSessionFactory().openSession();

        Topic getTopic = session.get(Topic.class, topic.getTopicId());
        printTopic(getTopic);

        session.close();
    }

    @Test
    public void relDaoModelTest() {
        Topic topic = new Topic(0, "relDaoModelTest", "topic-1");
        dao.entities.Test test_1 = new dao.entities.Test(0, topic.getDescription(), "test-1", topic);
        dao.entities.Test test_2 = new dao.entities.Test(0, topic.getDescription(), "test-2", topic);
        Question question_1 = new Question(0, topic.getDescription(), test_1);
        Question question_2 = new Question(0, topic.getDescription(), test_1);
        Question question_3 = new Question(0, topic.getDescription(), test_1);
        Question question_4 = new Question(0, topic.getDescription(), test_1);
        test_1.setQuestions(Arrays.asList(
                question_1,
                question_2,
                question_3,
                question_4
        ));
        topic.setTests(Arrays.asList(
                test_1,
                test_2
        ));
        DaoTopic daoTopic = new DaoTopic();
        daoTopic.save(topic);
        topic = daoTopic.findById(topic.getTopicId());
        printTopic(topic);
    }

}
