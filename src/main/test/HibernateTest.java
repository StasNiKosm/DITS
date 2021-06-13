import dao.HibernateSessionFactory;
import dao.TopicRepository;
import dao.entities.Literature;
import dao.entities.Question;
import dao.entities.Topic;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;
import provider.AppContextProvider;
import scriptDB.DBInitializer;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.function.Consumer;

public class HibernateTest {

    @Test
    public void checkConnection() {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.close();
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

    private static int count = 0;

    public static void printTopic(Topic topic) {
        System.out.println("COUNT = " + count++ + " | TOPIC : " + topic);
        topic.getTests().forEach(test -> {
            System.out.println("###\t " + test + " | topic_id=" + test.getTopic().getTopicId());
            test.getQuestions().forEach(question -> {
                System.out.println("###\t\t " + question + " | test_id=" + question.getTest().getTestId());
                question.getLiterature().forEach(literature -> {
                    System.out.println("###\t\t\t " + literature + " | test_id=" + literature.getQuestion().getQuestionId());
                });
            });
        });
        System.out.println();
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
    public void testRepository() throws SQLException {

        DBInitializer.init();

        Topic topic = new Topic(0, "relDaoModelTest", "topic-1");

        dao.entities.Test test_1 = new dao.entities.Test(0, topic.getDescription(), "test-1", topic);
        dao.entities.Test test_2 = new dao.entities.Test(0, topic.getDescription(), "test-2", topic);

        Question question_1 = new Question(0, topic.getDescription(), test_1);

        Literature literature_1 = new Literature(0, topic.getDescription(), question_1);

        question_1.setLiterature(Collections.singletonList(literature_1));
        test_1.setQuestions(Collections.singletonList(question_1));

        topic.setTests(Arrays.asList(test_1, test_2));

        TopicRepository topicRepository = AppContextProvider.getAppContext().getBean(TopicRepository.class);

        topicRepository.create(topic);

        {
            Session session = HibernateSessionFactory.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            topicRepository.save(topic, session);
            transaction.commit();

            topic = topicRepository.findAll(Topic.class, session).get(0);
            printTopic(topic);

            topic.setName("topic-1-update");
            transaction = session.beginTransaction();
            topicRepository.update(topic, session);
            transaction.commit();

            topic = topicRepository.findAll(Topic.class, session).get(0);
            printTopic(topic);

            topic.setName("topic-1-update-1");
            transaction = session.beginTransaction();
            topicRepository.update(topic, session);
            transaction.commit();

            topic = topicRepository.findAll(Topic.class, session).get(0);
            printTopic(topic);

            transaction = session.beginTransaction();
            topicRepository.delete(topic, session);
            transaction.commit();

            System.out.println(topicRepository.findAll(Topic.class, session).size());
        }

    }

}

