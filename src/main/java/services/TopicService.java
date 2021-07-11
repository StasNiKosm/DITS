package services;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.connection.HibernateSessionFactory;
import repository.dao.entities.Test;
import repository.dao.entities.Topic;
import repository.managers.eager.EagerManager;
import repository.managers.lazy.LazyManager;

import java.util.List;
import java.util.Set;

@Service
public class TopicService {

    private TopicServiceFacade lazyInstance;

    private TopicServiceFacade eagerInstance;

    @Autowired
    public void setTopicEagerManager(EagerManager<Topic> topicEagerManager) {
        eagerInstance = new TopicServiceFacade(topicEagerManager);
    }

    @Autowired
    public void setTopicLazyManager(LazyManager<Topic> topicLazyManager) {
        lazyInstance = new TopicServiceFacade(topicLazyManager);
    }

    public TopicServiceFacade getLazyInstance() {
        return lazyInstance;
    }

    public TopicServiceFacade getEagerInstance() {
        return eagerInstance;
    }

    public static class TopicServiceFacade {

        private final LazyManager<Topic> manager;

        private TopicServiceFacade(LazyManager<Topic> manager) {
            this.manager = manager;
        }

        public Topic getTopicById(int id) {
            return manager.read(id);
        }

        public Topic getTopicByName(String topicName) {
            return manager.executeSql("FROM Topic where name = '" + topicName + "'")
                    .stream()
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("No topic with name '" + topicName + "'"));
        }

        public Set<Test> getTestFromTopicByName(String topicName) {
            return getTestFromTopicById(getTopicByName(topicName).getTopicId());
        }

        public Set<Test> getTestFromTopicById(int id) {
            try(Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
                Topic topic = manager.read(id, session);
                Hibernate.initialize(topic.getTests());
                return topic.getTests();
            }
        }

        public List<Topic> getAllTopics() {
            return manager.getAll();
        }

        public void createTopic(String name, String description){
            Topic topic = new Topic();
            topic.setName(name);
            topic.setDescription(description);
            manager.create(topic);
        }

        public void createTopic(Topic topic){
            manager.create(topic);
        }

        public boolean isTopicWithName(String name){
            try{
                return this.getTopicByName(name) != null;
            } catch (IllegalArgumentException e){
                return false;
            }
        }

        public void updateTopic(Topic topic){
            manager.update(topic);
        }

        public void deleteTopic(Topic topic){
            manager.delete(topic);
        }

        public boolean containsTopicById(int topicId){
            return this.manager.read(topicId) != null;
        }
    }

}
