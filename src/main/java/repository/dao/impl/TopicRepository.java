package repository.dao.impl;

import repository.dao.entities.Topic;
import org.springframework.stereotype.Repository;

@Repository
public class TopicRepository extends AbstractRepository<Topic> {

    public TopicRepository() {
        super(Topic.class);
    }

}
