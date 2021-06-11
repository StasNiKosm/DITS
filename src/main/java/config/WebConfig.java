package config;

import dao.DaoPerson;
import dao.DaoQuestion;
import dao.DaoTest;
import dao.DaoTopic;
import dao.entities.Person;
import dao.entities.Question;
import dao.entities.Test;
import dao.entities.Topic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "dao", "controllers", "config" } )
public class WebConfig implements WebMvcConfigurer {

    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/WEB-INF/pages/**").addResourceLocations("/pages/");
    }

    @Bean
    public InternalResourceViewResolver getInternalResourceViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/pages/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Bean
    public DaoPerson getDaoPerson() {
        return new DaoPerson();
    }

    @Bean
    public Person getPerson() {
        return new Person();
    }

    @Bean
    public Topic getTopic() {
        return new Topic();
    }

    @Bean
    public DaoTopic getDaoTopic() {
        return new DaoTopic();
    }

    @Bean
    public Test getTest() {
        return new Test();
    }

    @Bean
    public DaoTest getDaoTest() {
        return new DaoTest();
    }

    @Bean
    public Question getQuestion() {
        return new Question();
    }

    @Bean
    public DaoQuestion getDaoQuestion() {
        return new DaoQuestion();
    }

}
