package services;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import repository.dao.entities.Statistic;
import repository.dao.entities.User;
import repository.managers.eager.EagerManager;
import repository.managers.lazy.LazyManager;

import javax.management.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

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

    }

    public void saveStatistic(Statistic statistic) {
        getLazyInstance().manager.create(statistic);
    }

}
