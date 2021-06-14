package config.app;

import dao.*;
import dao.entities.*;
import dao.services.TopicService;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class DaoConfig {

    @Bean
    @Scope(value = "prototype")
    public Topic topic() {
        return new Topic();
    }

    @Bean
    @Scope(value = "prototype")
    public Literature literature() {
        return new Literature();
    }

    @Bean
    @Scope(value = "prototype")
    public Test test() {
        return new Test();
    }

    @Bean
    @Scope(value = "prototype")
    public Question question() {
        return new Question();
    }

    @Bean
    public SessionFactory sessionFactory() {
        return HibernateSessionFactory.getSessionFactory();
    }

    @Bean
    public TopicRepository topicRepository() {
        return new TopicRepository();
    }

    @Bean
    public TopicService topicService() {
        return new TopicService(topicRepository(), sessionFactory());
    }

}
