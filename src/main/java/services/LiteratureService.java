package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.dao.entities.Literature;
import repository.managers.eager.EagerManager;
import repository.managers.lazy.LazyManager;

@Service
public class LiteratureService {

    private LiteratureServiceFacade lazyInstance;

    private LiteratureServiceFacade eagerInstance;

    @Autowired
    public void setLazyInstance(LazyManager<Literature> literatureLazyManager) {
        this.lazyInstance = new LiteratureServiceFacade(literatureLazyManager);
    }

    @Autowired
    public void setEagerInstance(EagerManager<Literature> literatureEagerManager) {
        this.lazyInstance = new LiteratureServiceFacade(literatureEagerManager);
    }

    public LiteratureServiceFacade getEagerInstance() {
        return eagerInstance;
    }

    public LiteratureServiceFacade getLazyInstance() {
        return lazyInstance;
    }

    public static class LiteratureServiceFacade {

        private LazyManager<Literature> manager;

        private LiteratureServiceFacade(LazyManager<Literature> manager) {
            this.manager = manager;
        }

    }

}
