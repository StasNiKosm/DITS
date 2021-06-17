package config.app;

import dao.*;
import dao.entities.*;
import dao.repository.*;
import dao.repository.services.*;
import dao.repository.services.eager.*;
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

    @Bean
    public TestRepository testRepository() {
        return new TestRepository();
    }

    @Bean
    public TestService testService() {
        return new TestService(testRepository(), sessionFactory());
    }

    @Bean
    public QuestionRepository questionRepository() {
        return new QuestionRepository();
    }

    @Bean
    public QuestionService questionService() {
        return new QuestionService(questionRepository(), sessionFactory());
    }

    @Bean
    public LiteratureRepository literatureRepository() {
        return new LiteratureRepository();
    }

    @Bean
    public LiteratureService literatureService() {
        return new LiteratureService(literatureRepository(), sessionFactory());
    }

    @Bean
    public LiteratureEagerRepository literatureEagerRepository() {
        return new LiteratureEagerRepository(null, literatureRepository(), sessionFactory());
    }

    @Bean
    public QuestionEagerRepository questionEagerRepository() {
        return new QuestionEagerRepository(literatureEagerRepository(), questionRepository(), sessionFactory());
    }

    @Bean
    public TestEagerRepository testEagerRepository() {
        return new TestEagerRepository(questionEagerRepository(), testRepository(), sessionFactory());
    }

    @Bean
    public TopicEagerRepository topicEagerRepository() {
        return new TopicEagerRepository(testEagerRepository(), topicRepository(), sessionFactory());
    }

}
