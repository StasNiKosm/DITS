package dao;

import dao.entities.Topic;
import org.hibernate.Session;

public class DaoTopic extends AbstractDao<Topic> {

    @Override
    public Topic findByID(int id) {
        return findByID(id, Topic.class);
    }

    @Override
    public void update(Topic topic) {
        commitOperation(session -> {
            session.update(topic);
        });
    }

    @Override
    public void deepUpdate(Topic topic, Session session) {
        session.update(topic);
        DaoTest daoTest = contextProvider.getBean(DaoTest.class);
        topic.getTests().forEach(
            test -> daoTest.deepUpdate(test, session)
        );
    }

    @Override
    public void save(Topic topic) {
        commitOperation(session -> {
            session.save(topic);
        });
    }

    @Override
    public void deepSave(Topic topic, Session session) {
        session.save(topic);
        DaoTest daoTest = contextProvider.getBean(DaoTest.class);
        topic.getTests().forEach(
            test -> daoTest.deepSave(test, session)
        );
    }

}
