package config.app;

import dao.DaoQuestion;
import dao.DaoTest;
import dao.DaoTopic;
import dao.entities.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class DaoConfig {

    @Bean
    @Scope(value = "prototype")
    public Topic getTopic() {
        return new Topic();
    }

    @Bean
    @Scope(value = "prototype")
    public Literature getLiterature() {
        return new Literature();
    }

    @Bean
    public DaoTopic getDaoTopic() {
        return new DaoTopic();
    }

    @Bean
    @Scope(value = "prototype")
    public Test getTest() {
        return new Test();
    }

    @Bean
    public DaoTest getDaoTest() {
        return new DaoTest();
    }

    @Bean
    public DaoLiterature getDaoLiterature() {
        return new DaoLiterature();
    }

    @Bean
    @Scope(value = "prototype")
    public Question getQuestion() {
        return new Question();
    }

    @Bean
    public DaoQuestion getDaoQuestion() {
        return new DaoQuestion();
    }

}
