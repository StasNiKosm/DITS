import dao.HibernateSessionFactory;
import dao.TopicRepository;
import dao.entities.Topic;
import org.hibernate.Session;
import provider.AppContextProvider;

public class Main {
    public static void main(String[] args) {
        AppContextProvider.getAppContext();
    }
}
