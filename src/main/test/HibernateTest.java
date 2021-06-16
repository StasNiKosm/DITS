import dao.HibernateSessionFactory;
import dao.entities.Literature;
import dao.entities.Question;
import dao.entities.Topic;
import dao.services.*;
import org.hibernate.Session;
import org.junit.Test;
import provider.AppContextProvider;

import java.util.Collections;

public class HibernateTest {

    @Test
    public void checkConnection() {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        System.out.println("Session open");
        session.close();
        System.out.println("Session closed");
    }

    private static void printTopic(String tittle, Topic topic) {
        System.out.println("\n" + tittle);
        System.out.println("topic : " + topic);
        if (topic == null)
            return;
        if (topic.getTests() != null)
            topic.getTests().forEach(test -> {
                System.out.println("\t| test : " + test);
                if (test.getQuestions() != null)
                    test.getQuestions().forEach(question -> {
                        System.out.println("\t\t| question : " + question);
                        if (question.getLiterature() != null)
                            question.getLiterature().forEach(literature -> {
                                System.out.println("\t\t\t| literature : " + literature);
                            });
                    });
            });
    }

    @Test
    public void saveTest() {

        RepositoryService<Topic> topicService = AppContextProvider.getAppContext().getBean(TopicService.class);

        Topic topic = AppContextProvider.getAppContext().getBean(Topic.class);
        dao.entities.Test test = AppContextProvider.getAppContext().getBean(dao.entities.Test.class);

        topic.setTests(Collections.singleton(test));

        topic.setName("topic-1");
        test.setName("test-1");
        test.setTopic(topic);

        topicService.create(topic);

        Topic readTopic;
        dao.entities.Test readTest;

        try(Session session = HibernateSessionFactory.getSessionFactory().openSession()) {

            readTopic = topicService.read(topic.getTopicId(), session);
            readTest = readTopic.getTests().iterator().next();

            printTopic("Saved topic", topic);
            printTopic("Loaded topic", readTopic);

            assert (topic.getName().equals(readTopic.getName()));
            assert (test.getName().equals(readTest.getName()));
        }

        readTopic.setName("topic-1-update");
        readTest.setName("test-1-update");

        topic = readTopic;
        test = readTopic.getTests().iterator().next();

        topicService.update(readTopic);

        try(Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            readTopic = topicService.read(topic.getTopicId(), session);
            readTest = readTopic.getTests().iterator().next();

            printTopic("Updated topic", topic);
            printTopic("After update topic", readTopic);

            assert (topic.getName().equals(readTopic.getName()));
            assert (test.getName().equals(readTest.getName()));
        }

        topicService.delete(readTopic);
        topic = topicService.read(readTopic.getTopicId());

        assert (topic == null);
        System.out.println("\nDeleted");

    }

    @Test
    public void deepRelTest() {
        RepositoryService<Topic> topicService = AppContextProvider.getAppContext().getBean(TopicService.class);

        Topic topic = AppContextProvider.getAppContext().getBean(Topic.class);
        dao.entities.Test test = AppContextProvider.getAppContext().getBean(dao.entities.Test.class);
        Question question = AppContextProvider.getAppContext().getBean(Question.class);
        Literature literature = AppContextProvider.getAppContext().getBean(Literature.class);

        question.setLiterature(Collections.singleton(literature));
        test.setQuestions(Collections.singleton(question));
        topic.setTests(Collections.singleton(test));

        topic.setName("topic-1");
        test.setName("test-1");
        question.setDescription("question-1");
        literature.setDescription("literature-1");

        test.setTopic(topic);
        question.setTest((test));
        literature.setQuestion((question));

        topicService.create(topic);

        Topic readTopic;

        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            readTopic = topicService.read(topic.getTopicId(), session);
            printTopic("Read topic", readTopic);
        }

        topicService.delete(readTopic);
        topic = topicService.read(readTopic.getTopicId());

        assert (topic == null);
        System.out.println("\nDeleted");

    }

    @Test
    public void servicesTest() {

        RepositoryService<Topic> topicService = AppContextProvider.getAppContext().getBean(TopicService.class);
        RepositoryService<dao.entities.Test> testService = AppContextProvider.getAppContext().getBean(TestService.class);
        RepositoryService<Question> questionService = AppContextProvider.getAppContext().getBean(QuestionService.class);
        RepositoryService<Literature> literatureService = AppContextProvider.getAppContext().getBean(LiteratureService.class);

        Topic topic = AppContextProvider.getAppContext().getBean(Topic.class);
        dao.entities.Test test = AppContextProvider.getAppContext().getBean(dao.entities.Test.class);
        Question question = AppContextProvider.getAppContext().getBean(Question.class);
        Literature literature = AppContextProvider.getAppContext().getBean(Literature.class);

        question.setLiterature(Collections.singleton(literature));
        test.setQuestions(Collections.singleton(question));
        topic.setTests(Collections.singleton(test));

        topic.setName("topic-1");
        test.setName("test-1");
        question.setDescription("question-1");
        literature.setDescription("literature-1");

        test.setTopic(topic);
        question.setTest((test));
        literature.setQuestion((question));

        topicService.create(topic);

        Topic readTopic;
        dao.entities.Test readTest;
        Question readQuestion;
        Literature readLiterature;

        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            readTopic = topicService.read(topic.getTopicId(), session);
            readTest = readTopic.getTests().iterator().next();
            readQuestion = readTest.getQuestions().iterator().next();
            readLiterature = readQuestion.getLiterature().iterator().next();
            printTopic("Read topic", readTopic);
        }

        readTest = testService.read(readTest.getTestId());
        readQuestion = questionService.read(readQuestion.getQuestionId());
        readLiterature = literatureService.read(readLiterature.getLiteratureId());

        assert (topic.getTopicId() == readTopic.getTopicId());
        assert (test.getTestId() == readTest.getTestId());
        assert (question.getQuestionId() == readQuestion.getQuestionId());
        assert (literature.getLiteratureId() == readLiterature.getLiteratureId());

        System.out.println("Services: ok");

        literatureService.delete(readLiterature);
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            readTopic = topicService.read(topic.getTopicId(), session);
            printTopic("Deleted literature", readTopic);
        }

        questionService.delete(readQuestion);
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            readTopic = topicService.read(topic.getTopicId(), session);
            printTopic("Deleted question", readTopic);
        }

        testService.delete(readTest);
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            readTopic = topicService.read(topic.getTopicId(), session);
            printTopic("Deleted test", readTopic);
        }

        readTopic = topicService.read(topic.getTopicId());
        topicService.delete(readTopic);

        readTopic = topicService.read(topic.getTopicId());
        assert (readTopic == null);

        System.out.println("Deleting: ok");
    }

}

