package dao;

import dao.entities.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Component
public class HibernateSessionFactory {

    private static EntityManagerFactory entityManagerFactory;

    private HibernateSessionFactory() {

    }

    public static EntityManager getEntityManager() {
        if (entityManagerFactory == null) {
            try {

//                Configuration configuration = new Configuration().configure();
//                // Добавить класс @Entity сюда и в hibernate.cfg.xml добавить Mapping
//                configuration.addAnnotatedClass(Topic.class);
//                configuration.addAnnotatedClass(Test.class);
//                configuration.addAnnotatedClass(Question.class);
//                configuration.addAnnotatedClass(Literature.class);
//                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
//                sessionFactory = configuration.buildSessionFactory(builder.build());

                entityManagerFactory = Persistence.createEntityManagerFactory("persistence-unit");

            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
        return getEntityManager();
    }
}
