package dao;

import dao.entities.Topic;
import org.hibernate.Session;

public class DaoTopic extends AbstractDao<Topic> {

    public Topic findById(int id) {
        return HibernateSessionFactory.getSessionFactory().openSession().get(Topic.class, id);
    }

    @Override
    public void update(Topic topic) {
        commitOperation(session -> {
            session.update(topic);
            //topic.getTests().forEach(AppContextProvider.getAppContext().getBean(DaoTest.class)::update);
        });
    }

    @Override
    public void save(Topic topic) {
        commitOperation(session -> save(topic, session));
    }

    public void save(Topic topic, Session session) {
        commitOperation(session, session_ -> {
            session_.save(topic);
            DaoTest daoTest = new DaoTest();
            topic.getTests().forEach(test -> daoTest.save(test, session_));
            //topic.getTests().forEach(AppContextProvider.getAppContext().getBean(DaoTest.class)::update);
        });
    }




}
