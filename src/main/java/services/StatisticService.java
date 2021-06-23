package services;

import org.springframework.beans.factory.annotation.Autowired;
import repository.dao.entities.Statistic;
import repository.managers.eager.EagerManager;
import repository.managers.lazy.LazyManager;

public class StatisticService {

    private StatisticServiceFacade lazyInstance;

    private StatisticServiceFacade eagerInstance;

    @Autowired
    public void setLazyInstance(LazyManager<Statistic> statisticLazyManager) {
        this.lazyInstance = new StatisticServiceFacade(statisticLazyManager);
    }

    @Autowired
    public void setEagerInstance(EagerManager<Statistic> statisticEagerManager) {
        this.lazyInstance = new StatisticServiceFacade(statisticEagerManager);
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

    }

}
