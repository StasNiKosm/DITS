package config.app;

import config.CustomSuccessHandler;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
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
import services.MemberServiceImpl;
import services.UserService;


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
    @Scope(value = "prototype")
    public User user() {
        return new User();
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

    @Bean
    public UserService userService() {
        return new UserService();
    }

    @Bean
    public CustomSuccessHandler customSuccessHandler() {
        return new CustomSuccessHandler();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new MemberServiceImpl();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
}
