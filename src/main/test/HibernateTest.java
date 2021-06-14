import dao.HibernateSessionFactory;
import dao.entities.Topic;
import dao.services.RepositoryService;
import dao.services.TopicService;
import org.hibernate.Session;
import org.junit.Test;
import provider.AppContextProvider;
import scriptDB.DBInitializer;

import java.sql.SQLException;
import java.util.Collections;

public class HibernateTest {

    @Test
    public void checkConnection() {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.close();
    }

    @Test
    public void saveTest() throws SQLException {
        DBInitializer.init();

        Topic topic = AppContextProvider.getAppContext().getBean(Topic.class);
        dao.entities.Test test = AppContextProvider.getAppContext().getBean(dao.entities.Test.class);

        topic.setTests(Collections.singleton(test));

        topic.setName("topic-1");
        test.setName("test-1");
        test.setTopic(topic);

        RepositoryService<Topic> topicService = AppContextProvider.getAppContext().getBean(TopicService.class);

        topicService.create(topic);

        Topic readTopic = topicService.read(topic.getTopicId());

        System.out.println("Topics compare:");
        System.out.println(topic);
        System.out.println(readTopic);

        System.out.println("Test compare:");
        System.out.println(topic.getTests().iterator().next());
        System.out.println(readTopic.getTests().iterator().next());

    }

}

