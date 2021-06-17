package dao.repository.services.eager;

import dao.entities.Test;
import dao.entities.Topic;
import dao.intefaces.DaoRepository;
import dao.intefaces.EagerRepositoryService;
import dao.repository.services.TopicService;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TopicEagerRepository extends TopicService implements EagerRepositoryService<Topic> {

    @Autowired
    private final EagerRepositoryService<Test> testEagerRepository;

    public TopicEagerRepository(@Autowired EagerRepositoryService<Test> testEagerRepository, @Autowired DaoRepository<Topic> repository, @Autowired SessionFactory sessionFactory) {
        super(repository, sessionFactory);
        this.testEagerRepository = testEagerRepository;
        System.out.println(TopicEagerRepository.class.getName());
    }

    @Override
    public Topic load(Topic topic, Session session) {
        topic = getRepository().findById(getRepository().getTemplatedClass(), topic.getTopicId(), session);
        Hibernate.initialize(topic.getTests());
        testEagerRepository.loadAll(topic.getTests(), session);
        return topic;
    }

}
