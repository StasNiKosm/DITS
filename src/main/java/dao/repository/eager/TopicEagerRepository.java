package dao.repository.eager;

import dao.entities.Test;
import dao.entities.Topic;
import dao.repository.DaoRepository;
import dao.services.TopicService;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TopicEagerRepository extends TopicService implements EagerRepositoryLoader<Topic> {

    @Autowired
    private EagerRepositoryLoader<Test> testEagerRepository;

    public TopicEagerRepository(@Autowired EagerRepositoryLoader<Test> testEagerRepository, @Autowired DaoRepository<Topic> repository, @Autowired SessionFactory sessionFactory) {
        super(repository, sessionFactory);
        this.testEagerRepository = testEagerRepository;
        System.out.println(TopicEagerRepository.class.getName());
    }

    @Override
    public Topic load(Topic topic, Session session) {
        topic = getRepository().findById(getRepository().getTemplatedClass(), topic.getTopicId(), session);
        Hibernate.initialize(topic.getTests());
        testEagerRepository.loadAll(topic.getTests());
        return topic;
    }

}
