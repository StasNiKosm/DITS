package dao;

import dao.entities.Topic;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class TopicRepository extends AbstractDaoRepository<Topic> {

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

}
