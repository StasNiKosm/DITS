
import providers.AppContextProvider;
import repository.dao.entities.Literature;
import repository.managers.eager.impl.LiteratureEagerManager;
import services.TestService;
import services.TopicService;

public class Main {
    public static void main(String[] args) {
        TopicService topicService = AppContextProvider.getAppContext().getBean(TopicService.class);
        TestService testService = AppContextProvider.getAppContext().getBean(TestService.class);
        System.out.println(testService.getTestsAsJson(topicService.getEagerInstance().getTestFromTopicById(1)));
    }
}
