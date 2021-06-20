package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.dao.entities.Topic;
import repository.managers.eager.EagerManager;
import repository.managers.lazy.LazyManager;

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

        public TopicServiceFacade(LazyManager<Topic> manager) {
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

    }

}
