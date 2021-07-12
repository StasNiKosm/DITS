package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.dao.entities.Literature;
import repository.managers.eager.EagerManager;
import repository.managers.lazy.LazyManager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
        this.eagerInstance = new LiteratureServiceFacade(literatureEagerManager);
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

        public List<Literature> getLiteratureByQuestionId(int questionId) {
            return manager.executeSql("From Literature where questionid = " + questionId);
        }

        public List<Literature> load(Collection<Literature> collection) {
            ArrayList<Literature> list = new ArrayList<Literature>();
            collection.forEach(lit -> list.add(manager.read(lit.getLiteratureId())));
            return list;
        }

        public void createLiterature(Literature literature){
            manager.create(literature);
        }

        public void updateLiteratureById(int literatureId, String literatureDescription){
            Literature literature = manager.read(literatureId);
            literature.setDescription(literatureDescription);
            manager.update(literature);
        }

        public void deleteLiteratureById(int literatureId){
            manager.delete(manager.read(literatureId));
        }

        public boolean containsLiteratureById(int literatureId){
            return manager.read(literatureId) != null;
        }
    }

}
