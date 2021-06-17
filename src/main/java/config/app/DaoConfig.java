package config.app;

import repository.dao.entities.*;
import repository.dao.impl.LiteratureRepository;
import repository.dao.impl.QuestionRepository;
import repository.dao.impl.TestRepository;
import repository.dao.impl.TopicRepository;
import repository.managers.eager.impl.LiteratureEagerManager;
import repository.managers.eager.impl.QuestionEagerManager;
import repository.managers.eager.impl.TestEagerManager;
import repository.managers.eager.impl.TopicEagerManager;
import repository.managers.lazy.impl.LiteratureLazyManager;
import repository.managers.lazy.impl.QuestionLazyManager;
import repository.managers.lazy.impl.TestLazyManager;
import repository.managers.lazy.impl.TopicLazyManager;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import repository.connection.HibernateSessionFactory;

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
    public TopicLazyManager topicLazyManager() {
        return new TopicLazyManager(topicRepository(), sessionFactory());
    }

    @Bean
    public TestRepository testRepository() {
        return new TestRepository();
    }

    @Bean
    public TestLazyManager testLazyManager() {
        return new TestLazyManager(testRepository(), sessionFactory());
    }

    @Bean
    public QuestionRepository questionRepository() {
        return new QuestionRepository();
    }

    @Bean
    public QuestionLazyManager questionLazyManager() {
        return new QuestionLazyManager(questionRepository(), sessionFactory());
    }

    @Bean
    public LiteratureRepository literatureRepository() {
        return new LiteratureRepository();
    }

    @Bean
    public LiteratureLazyManager literatureLazyManager() {
        return new LiteratureLazyManager(literatureRepository(), sessionFactory());
    }

    @Bean
    public LiteratureEagerManager literatureEagerRepository() {
        return new LiteratureEagerManager(null, literatureRepository(), sessionFactory());
    }

    @Bean
    public QuestionEagerManager questionEagerManager() {
        return new QuestionEagerManager(literatureEagerRepository(), questionRepository(), sessionFactory());
    }

    @Bean
    public TestEagerManager testEagerManager() {
        return new TestEagerManager(questionEagerManager(), testRepository(), sessionFactory());
    }

    @Bean
    public TopicEagerManager topicEagerManager() {
        return new TopicEagerManager(testEagerManager(), topicRepository(), sessionFactory());
    }

}
