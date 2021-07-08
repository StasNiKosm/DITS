package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.dao.entities.Link;
import repository.managers.eager.EagerManager;
import repository.managers.lazy.LazyManager;

@Service
public class LinkService {

    private LinkServiceFacade lazyInstance;

    private LinkServiceFacade eagerInstance;

    @Autowired
    public void setLazyInstance(LazyManager<Link> linkLazyManager) {
        this.lazyInstance = new LinkServiceFacade(linkLazyManager);
    }

    @Autowired
    public void setEagerInstance(EagerManager<Link> linkEagerManager) {
        this.lazyInstance = new LinkServiceFacade(linkEagerManager);
    }

    public LinkServiceFacade getEagerInstance() {
        return eagerInstance;
    }

    public LinkServiceFacade getLazyInstance() {
        return lazyInstance;
    }

    public static class LinkServiceFacade {

        private LazyManager<Link> manager;

        private LinkServiceFacade(LazyManager<Link> manager) {
            this.manager = manager;
        }



    }

}