
import org.hibernate.Session;
import org.springframework.security.crypto.password.PasswordEncoder;
import providers.AppContextProvider;
import repository.dao.entities.Statistic;
import repository.dao.entities.Test;
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

import java.util.List;

public class Main {
    public static void main(String[] args) {
//        TopicService topicService = AppContextProvider.getAppContext().getBean(TopicService.class);
//        TestService testService = AppContextProvider.getAppContext().getBean(TestService.class);
//        System.out.println(testService.getTestsAsJson(topicService.getEagerInstance().getTestFromTopicById(1)));
//        System.out.println(AppContextProvider.getAppContext().getBean(PasswordEncoder.class).encode("admin"));
//
//        TestLazyManager testLazyManager = AppContextProvider.getAppContext().getBean(TestEagerManager.class);
//        Test test = testLazyManager.getRepository().findById(Test.class, 4, testLazyManager.getSessionFactory().openSession());
//        System.out.println(test);
//        testLazyManager.delete(test);



        UserService userService = AppContextProvider.getAppContext().getBean(UserService.class);
        User user = userService.getLazyInstance().getUserById(2289);
        System.out.println(user);

        DeleteUserWithHisStatisticResolver deleteUserWithHisStatisticResolver = AppContextProvider.getAppContext().getBean(DeleteUserWithHisStatisticResolver.class);
        deleteUserWithHisStatisticResolver.deleteUserWithHisStatistic(user);


//        UserLazyManager userLazyManager = AppContextProvider.getAppContext().getBean(UserEagerManager.class);
//        userLazyManager.delete(user);

//        StatisticLazyManager statisticLazyManager = AppContextProvider.getAppContext().getBean(StatisticEagerManager.class);
//        List<Statistic> listStatistics = statisticLazyManager.getAll();
//        System.out.println(listStatistics);
//        for (int i = 0; i < listStatistics.size(); i++) {
//            if(listStatistics.get(i).getUser().getUserId() == 2280) {
//                System.out.println(listStatistics.get(i));
//                Session session = statisticLazyManager.getSessionFactory().openSession();
//                session.beginTransaction();
//                statisticLazyManager.getRepository().delete(listStatistics.get(i), session);
//                session.getTransaction().commit();
//
//            }
//
//        }
//        userService.getLazyInstance().deleteUser(user);

    }
}
