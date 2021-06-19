
import providers.AppContextProvider;
import repository.dao.entities.Literature;
import repository.managers.eager.impl.LiteratureEagerManager;

public class Main {
    public static void main(String[] args) {
        LiteratureEagerManager managerAppContextProvider = AppContextProvider.getAppContext().getBean(LiteratureEagerManager.class);
        Literature literature = managerAppContextProvider.read(1);
        System.out.println(literature.getLinks().iterator().next().getLink());
    }
}
