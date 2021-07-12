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

        public void createLink(Link link){
            manager.create(link);
        }

        public void updateLinkById(int linkId, String link){
            Link link1 = manager.read(linkId);
            link1.setLink(link);
            manager.update(link1);
        }

        public void deleteLinkById(int linkId){
            manager.delete(manager.read(linkId));
        }

        public boolean containsLinkById(int linkId){
            return manager.read(linkId) != null;
        }
    }

}