
import org.springframework.security.crypto.password.PasswordEncoder;
import providers.AppContextProvider;
import services.TestService;
import services.TopicService;

public class Main {
    public static void main(String[] args) {
//        TopicService topicService = AppContextProvider.getAppContext().getBean(TopicService.class);
//        TestService testService = AppContextProvider.getAppContext().getBean(TestService.class);
//        System.out.println(testService.getTestsAsJson(topicService.getEagerInstance().getTestFromTopicById(1)));
        System.out.println(AppContextProvider.getAppContext().getBean(PasswordEncoder.class).encode("admin"));

    }
}
