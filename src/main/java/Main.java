
import org.hibernate.Session;
import org.springframework.security.crypto.password.PasswordEncoder;
import providers.AppContextProvider;
import repository.dao.entities.Statistic;
import repository.dao.entities.Test;
import repository.dao.entities.Topic;
import repository.dao.entities.User;
import repository.managers.eager.impl.StatisticEagerManager;
import repository.managers.eager.impl.TestEagerManager;
import repository.managers.eager.impl.UserEagerManager;
import repository.managers.lazy.impl.StatisticLazyManager;
import repository.managers.lazy.impl.TestLazyManager;
import repository.managers.lazy.impl.UserLazyManager;
import services.StatisticService;
import services.TestService;
import services.TopicService;
import services.UserService;
import services.user.DeleteUserWithHisStatisticResolver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
//        TopicService topicService = AppContextProvider.getAppContext().getBean(TopicService.class);
//        Topic topic = topicService.getEagerInstance().getTopicById(2);
//        topicService.getLazyInstance().deleteTopic(topic);


    }
}
