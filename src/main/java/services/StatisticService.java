package services;

import org.hibernate.*;
import org.hibernate.type.DoubleType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import providers.AppContextProvider;
import repository.connection.HibernateSessionFactory;
import repository.dao.entities.Statistic;
import repository.dao.entities.User;
import repository.managers.eager.EagerManager;
import repository.managers.lazy.LazyManager;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Map;

import static repository.connection.HibernateSessionFactory.getEntityManagerFactory;

public class StatisticService {

    private StatisticServiceFacade lazyInstance;

    private StatisticServiceFacade eagerInstance;

    @Autowired
    public void setLazyInstance(LazyManager<Statistic> statisticLazyManager) {
        this.lazyInstance = new StatisticServiceFacade(statisticLazyManager);
    }

    @Autowired
    public void setEagerInstance(EagerManager<Statistic> statisticEagerManager) {
        this.eagerInstance = new StatisticServiceFacade(statisticEagerManager);
    }

    public StatisticServiceFacade getEagerInstance() {
        return eagerInstance;
    }

    public StatisticServiceFacade getLazyInstance() {
        return lazyInstance;
    }

    public static class StatisticServiceFacade {

        private LazyManager<Statistic> manager;

        private StatisticServiceFacade(LazyManager<Statistic> manager) {
            this.manager = manager;
        }

        public List<Statistic> getStatisticFromUser(UserSecurityService.AuthorizedUser user, int page, int onPageCount) {
            CriteriaBuilder criteria = manager.getSessionFactory().getCriteriaBuilder();
            CriteriaQuery<Statistic> criteriaQuery = criteria.createQuery(Statistic.class);
            Root<Statistic> root = criteriaQuery.from(Statistic.class);
            criteriaQuery.select(root)
                    .where(criteria.equal(root.get("user"), user.getUser().getUserId()))
                    .orderBy(criteria.desc(root.get("date")));

            Session session = manager.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            TypedQuery<Statistic> statisticTypedQuery = session.createQuery(criteriaQuery).setFirstResult(page * onPageCount).setMaxResults(onPageCount);
            List<Statistic> statistics = statisticTypedQuery.getResultList();
            tx.commit();
            session.close();

            return statistics;
        }

        public void deleteStatisticByUser(User user){
            List<Statistic> statistics = manager.getAll();
            for (int i = 0; i < statistics.size(); i++) {
                if(statistics.get(i).getUser().getUserId() == user.getUserId()) {
                    manager.delete(statistics.get(i));
                }
            }
        }

        public boolean hasUserStatistic(User user){
            List<Statistic> statistics = manager.getAll();
            for (int i = 0; i < statistics.size(); i++) {
                if(statistics.get(i).getUser().getUserId() == user.getUserId()) {
                    return true;
                }
            }
            return false;
        }

        public List<String[]> getQuestionStatistics() {
            try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
                return session.createNativeQuery(
                        "select q.description des, count(q.questionid) count, CAST(CAST(sum(s.correct) AS DECIMAL(7,2) )/count(q.questionid)*100 AS DECIMAL(7,2) ) proc " +
                                "from question q left join statistic s on q.questionid = s.questionid where statisticid is not null " +
                                "GROUP BY q.questionid union select q.description, 0 as count, null as proc from question q " +
                                "left join statistic s on q.questionid = s.questionid where statisticid is null GROUP BY q.questionid order by 2 DESC")
                        .addScalar("des", StringType.INSTANCE)
                        .addScalar("count", StringType.INSTANCE)
                        .addScalar("proc", StringType.INSTANCE)
                        .list();
            }
        }

        public List<String[]> getTestStatistics() {
            try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
                return session.createNativeQuery("select result.name, result.count count, CAST(CAST(sum(result.sum_by_question) AS DECIMAL(7,2) )/sum(result.count)*100 AS DECIMAL(7,2) ) proc\n" +
                        "from (select name, count(name) count, sum(correct) sum_by_question from (statistic s full join question q on q.questionid = s.questionid)\n" +
                        "full join test t on q.testid = t.testid where correct is not null group by q.description, name) as result group by result.name, result.count\n" +
                        "union select name, 0 as count, null from test order by count DESC")
                        .addScalar("name", StringType.INSTANCE)
                        .addScalar("count", StringType.INSTANCE)
                        .addScalar("proc", StringType.INSTANCE)
                        .list();
            }
        }

        public List<String[]> getUserStatistics() {
            try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
                return session.createNativeQuery("select login, firstname, lastname, name, count, CAST(CAST(sum(result.sum_by_question) AS DECIMAL(7,2) )/sum(result.count)*100 AS DECIMAL(7,2) ) proc\n" +
                        "from (select login, firstname, lastname, name, count(name) count, sum(correct) sum_by_question\n" +
                        "from tuser left join statistic s on tuser.userid = s.userid left join question q on q.questionid = s.questionid\n" +
                        "left join test t on t.testid = q.testid group by login, firstname, lastname, name, q.description) as result\n" +
                        "group by login, firstname, lastname, name, count order by count DESC")
                        .addScalar("login", StringType.INSTANCE)
                        .addScalar("firstname", StringType.INSTANCE)
                        .addScalar("lastname", StringType.INSTANCE)
                        .addScalar("name", StringType.INSTANCE)
                        .addScalar("count", StringType.INSTANCE)
                        .addScalar("proc", StringType.INSTANCE)
                        .list();
            }
        }


    }

    public void saveStatistic(Statistic statistic) {
        getLazyInstance().manager.create(statistic);
    }

}
