
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

import javax.persistence.criteria.CriteriaBuilder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {
//        TopicService topicService = AppContextProvider.getAppContext().getBean(TopicService.class);
//        Topic topic = topicService.getEagerInstance().getTopicById(2);
//        topicService.getLazyInstance().deleteTopic(topic);
//Аппаратными или физическими ресурсами 1 2253
//        LiteratureService literatureService = AppContextProvider.getAppContext().getBean(LiteratureService.class);
//        AnswerService answerService = AppContextProvider.getAppContext().getBean(AnswerService.class);
//        TestService testService = AppContextProvider.getAppContext().getBean(TestService.class);
//        Test test = testService.getEagerInstance().getTestById(2252);
//        Answer answer = test.getQuestions().iterator().next().getAnswers().iterator().next();
//        test.getQuestions().iterator().next().getAnswers().remove(answer);
//        testService.getEagerInstance().updateTest(test);
//        System.out.println(test.getQuestions().iterator().next().getAnswers().iterator().next().getQuestion().getQuestionId());
        //test.getQuestions().iterator().next().getLiterature()
        //  System.out.println(literatureService.getEagerInstance().getLiteratureByQuestionId(22));
//        System.out.println(answerService.getLazyInstance().getAllAnswerByQuestionId(22));

        Supplier<A> i = A::new;
        A a = i.get();
        System.out.println(a);

        byte b1 = 1;
        byte b2 = 1;
        byte br = (byte) (b1 + b2);


    }
}


class A{
    int a;
    int b;
    int c;
    public A() {

    }
    public A(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public String toString() {
        return "A{" +
                "a=" + a +
                ", b=" + b +
                ", c=" + c +
                '}';
    }
}

interface I{
    final int a = 0;
    A create(int a, int b, int c);
}

class TestStatisticByUser{
    private String testName;
    private String testDescription;
    private int count;
    private int avgProc;
    /* геттеры, сеттеры, контрукторы */
}

class UserStatistic{
    private String firstName;
    private String lastName;
    private List<TestStatisticByUser> testsStatistics;
    /* геттеры, сеттеры, контрукторы */
}

class TestStatistic{
    private String testName;
    private String testDescription;
    private int count;
    private int avgProc;
    /* геттеры, сеттеры, контрукторы */
}

class TopicStaticByTests{
    private String topicName;
    private String topicDescription;
    private List<TestStatistic> testStatistic;
    /* геттеры, сеттеры, контрукторы */
}

class QuestionStatistic{
    private String questionDescription;
    private int count;
    private int avgProc;
    /* геттеры, сеттеры, контрукторы */
}

class TestStatisticByQuestions{
    private String testName;
    private String testDescription;
    private List<QuestionStatistic> questionStatistic;
    /* геттеры, сеттеры, контрукторы */
}

abstract class StatisticFasade{

    public abstract UserStatistic getUserStatistic(int userId);

    public abstract List<UserStatistic> getAllUserStatistic();

    public abstract List<TopicStaticByTests> getAllTopicStaticByTests();

    public abstract List<TestStatisticByQuestions> getAllTestStatisticByQuestions();
}
