package dao;

import dao.entities.Topic;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public class TopicRepository extends AbstractRepository<Topic> {

    public TopicRepository() {
        super(Topic.class);
    }

    @Override
    public Topic findById(int id, Session session) {
        Topic topic = super.findById(id, session);
        Hibernate.initialize(topic.getTests());
        return topic;
    }
}
