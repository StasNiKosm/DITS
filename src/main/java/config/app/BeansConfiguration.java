package config.app;

import config.CustomSuccessHandler;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import repository.dao.entities.*;
import repository.dao.impl.*;
import repository.managers.eager.impl.*;
import repository.managers.lazy.impl.*;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import repository.connection.HibernateSessionFactory;
import services.*;

@Configuration
public class BeansConfiguration {

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
    @Scope(value = "prototype")
    public User user() {
        return new User();
    }

    @Bean
    @Scope(value = "prototype")
    public Answer answer() {
        return new Answer();
    }

    @Bean
    @Scope(value = "prototype")
    public Link link() {
        return new Link();
    }

    @Bean
    @Scope(value = "prototype")
    public Statistic statistic() {
        return new Statistic();
    }

    @Bean
    public SessionFactory sessionFactory() {
        return HibernateSessionFactory.getSessionFactory();
    }

    @Bean
    public UserRepository userRepository() {
        return new UserRepository();
    }

    @Bean
    public TopicRepository topicRepository() {
        return new TopicRepository();
    }

    @Bean
    public UserLazyManager userLazyManager() {
        return new UserLazyManager(userRepository(), sessionFactory());
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
    public AnswerLazyManager answerLazyManager() {
        return new AnswerLazyManager(answerRepository(), sessionFactory());
    }

    @Bean
    public LinkLazyManager linkLazyManager() {
        return new LinkLazyManager(linkRepository(), sessionFactory());
    }

    @Bean
    public StatisticLazyManager statisticLazyManager() {
        return new StatisticLazyManager(statisticRepository(), sessionFactory());
    }

    @Bean
    public LiteratureEagerManager literatureEagerRepository() {
        return new LiteratureEagerManager(linkEagerManager(), literatureRepository(), sessionFactory());
    }

    @Bean
    public QuestionEagerManager questionEagerManager() {
        return new QuestionEagerManager(literatureEagerRepository(), statisticEagerManager(), questionRepository(), sessionFactory());
    }

    @Bean
    public LinkRepository linkRepository() {
        return new LinkRepository();
    }

    @Bean
    public StatisticRepository statisticRepository() {
        return new StatisticRepository();
    }

    @Bean
    public AnswerRepository answerRepository() {
        return new AnswerRepository();
    }

    @Bean
    public TestEagerManager testEagerManager() {
        return new TestEagerManager(questionEagerManager(), testRepository(), sessionFactory());
    }

    @Bean
    public LinkEagerManager linkEagerManager() {
        return new LinkEagerManager(linkRepository(), sessionFactory());
    }

    @Bean
    public AnswerEagerManager answerEagerManager() {
        return new AnswerEagerManager(answerRepository(), sessionFactory());
    }

    @Bean
    public StatisticEagerManager statisticEagerManager() {
        return new StatisticEagerManager(statisticRepository(), sessionFactory());
    }

    @Bean
    public TopicEagerManager topicEagerManager() {
        return new TopicEagerManager(testEagerManager(), topicRepository(), sessionFactory());
    }

    @Bean
    public CustomSuccessHandler customSuccessHandler() {
        return new CustomSuccessHandler();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserSecurityService userSecurityService() {
        return new UserSecurityService();
    }

    @Bean
    public UserEagerManager userEagerManager() {
        return new UserEagerManager(userRepository(), sessionFactory());
    }

    @Bean
    public UserService userService() {
        return new UserService();
    }

    @Bean
    public TopicService topicService() {
        return new TopicService();
    }

    @Bean
    public TestService testService() {
        return new TestService();
    }

    @Bean
    public AnswerService answerService() {
        return new AnswerService();
    }

    @Bean
    public LinkService linkService() {
        return new LinkService();
    }

    @Bean
    public QuestionService questionService() {
        return new QuestionService();
    }

    @Bean
    public StatisticService statisticService() {
        return new StatisticService();
    }

}
