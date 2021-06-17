package repository.managers.eager.impl;

import repository.dao.entities.Test;
import repository.dao.entities.Topic;
import repository.dao.DaoRepository;
import repository.managers.eager.EagerManager;
import repository.managers.lazy.impl.TopicLazyManager;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TopicEagerManager extends TopicLazyManager implements EagerManager<Topic> {

    @Autowired
    private final EagerManager<Test> testEagerRepository;

    public TopicEagerManager(@Autowired EagerManager<Test> testEagerRepository, @Autowired DaoRepository<Topic> repository, @Autowired SessionFactory sessionFactory) {
        super(repository, sessionFactory);
        this.testEagerRepository = testEagerRepository;
    }

    @Override
    public Topic load(Topic topic, Session session) {
        topic = getRepository().findById(getRepository().getTemplatedClass(), topic.getTopicId(), session);
        Hibernate.initialize(topic.getTests());
        testEagerRepository.loadAll(topic.getTests(), session);
        return topic;
    }

}
