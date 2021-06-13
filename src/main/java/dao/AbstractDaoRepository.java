package dao;

import org.hibernate.SessionFactory;
import org.springframework.context.support.AbstractApplicationContext;
import provider.AppContextProvider;

public abstract class AbstractDaoRepository<T> implements DaoRepository<T> {

    protected static AbstractApplicationContext contextProvider = AppContextProvider.getAppContext();

    protected SessionFactory sessionFactory;

    @Override
    public SessionFactory getSessionFactoryBean() {
        return sessionFactory;
    }
}
