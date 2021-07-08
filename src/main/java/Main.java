
import org.hibernate.Session;
import org.springframework.security.crypto.password.PasswordEncoder;
import providers.AppContextProvider;
import repository.dao.entities.*;
import repository.managers.eager.impl.StatisticEagerManager;
import repository.managers.eager.impl.TestEagerManager;
import repository.managers.eager.impl.UserEagerManager;
import repository.managers.lazy.impl.StatisticLazyManager;
import repository.managers.lazy.impl.TestLazyManager;
import repository.managers.lazy.impl.UserLazyManager;
import services.*;
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
//Аппаратными или физическими ресурсами 1 2253
        LiteratureService literatureService = AppContextProvider.getAppContext().getBean(LiteratureService.class);
        AnswerService answerService = AppContextProvider.getAppContext().getBean(AnswerService.class);
        TestService testService = AppContextProvider.getAppContext().getBean(TestService.class);
        Test test = testService.getEagerInstance().getTestById(2252);
        Answer answer = test.getQuestions().iterator().next().getAnswers().iterator().next();
        test.getQuestions().iterator().next().getAnswers().remove(answer);
        testService.getEagerInstance().updateTest(test);
//        System.out.println(test.getQuestions().iterator().next().getAnswers().iterator().next().getQuestion().getQuestionId());
        //test.getQuestions().iterator().next().getLiterature()
      //  System.out.println(literatureService.getEagerInstance().getLiteratureByQuestionId(22));
//        System.out.println(answerService.getLazyInstance().getAllAnswerByQuestionId(22));


    }
}
