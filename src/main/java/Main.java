import dao.HibernateSessionFactory;
import dao.TopicRepository;
import dao.entities.Topic;
import org.hibernate.Session;
import provider.AppContextProvider;

public class Main {
    public static void main(String[] args) {
        TopicRepository topicRepository = AppContextProvider.getAppContext().getBean(TopicRepository.class);
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Topic topic = topicRepository.findAll(Topic.class, session).get(0);
        session.close();
    }
}
