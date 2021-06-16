package dao;

import dao.entities.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Component
public class HibernateSessionFactory {
    private static SessionFactory sessionFactory;

    private HibernateSessionFactory() {

    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {

//                Configuration configuration = new Configuration().configure();
//                // Добавить класс @Entity сюда и в hibernate.cfg.xml добавить Mapping
//                configuration.addAnnotatedClass(Topic.class);
//                configuration.addAnnotatedClass(Test.class);
//                configuration.addAnnotatedClass(Question.class);
//                configuration.addAnnotatedClass(Literature.class);
//                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
//                sessionFactory = configuration.buildSessionFactory(builder.build());

                EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence-unit");
                sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);

            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
        return sessionFactory;
    }
}
