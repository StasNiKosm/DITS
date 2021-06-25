package services.user;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.dao.entities.Answer;
import repository.dao.entities.Literature;
import repository.dao.entities.Question;
import repository.dao.entities.Test;
import services.*;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TestResultCheckerService {

    private static AnswerService answerService;

    private static QuestionService questionService;

    private static LiteratureService literatureService;

    private static TestService testService;

    @Autowired
    public void setAnswerService(AnswerService answerService) {
        TestResultCheckerService.answerService = answerService;
    }

    @Autowired
    public void setQuestionService(QuestionService questionService) {
        TestResultCheckerService.questionService = questionService;
    }

    @Autowired
    public void setLiteratureService(LiteratureService literatureService) {
        TestResultCheckerService.literatureService = literatureService;
    }

    @Autowired
    public void setTestService(TestService testService) {
        TestResultCheckerService.testService = testService;
    }

    public Result createReport(Map<String, String[]> parameterMap, Test test, UserSecurityService.AuthorizedUser user, Date date) {

        parameterMap.entrySet().forEach(entry -> {
            System.out.print(entry.getKey() + " -> ");
            Arrays.asList(entry.getValue()).forEach(value -> System.out.print(value + " "));
            System.out.println();
        });

        List<Answer> markedAnswers = new ArrayList<>();
        markedAnswers.addAll(parseParamMap(parameterMap));

        List<Answer> answersFromParameterMap = parseParamMap(parameterMap);
        List<Answer> correctAnswer = new ArrayList<>(getCorrectAnswersFromTest(test));

        List<Answer> incorrectAnswers = new ArrayList<>();

        answersFromParameterMap.forEach(answer -> {
           if (answer.getCorrect() == 0) {
               incorrectAnswers.add(answer);
           }
           if (answer.getCorrect() == 1) {
               correctAnswer.remove(answer);
           }
        });

        incorrectAnswers.addAll(correctAnswer);

        System.out.println(correctAnswer);

        return new Result(test, incorrectAnswers, markedAnswers);
    }

    private List<Answer> getCorrectAnswersFromTest(Test test) {
        List<Answer> answerList = new ArrayList<>();
        test.getQuestions().forEach(question -> answerList.addAll(getCorrectAnswerFromQuestion(question)));
        return answerList;
    }

    private List<Answer> getCorrectAnswerFromQuestion(Question question) {
        return answerService.getEagerInstance().getCorrectAnswerFromQuestion(question.getQuestionId());
    }

    private List<Answer> parseParamMap(Map<String, String[]> parameterMap) {
        return parameterMap.keySet().stream().filter(strings -> strings.startsWith("answer")).map(strings -> {
            String[] words = strings.split("-");
            System.out.println(words[words.length - 1]);
            return answerService.getEagerInstance().getAnswerById(Integer.parseInt(words[words.length - 1]));
        }).collect(Collectors.toList());
    }

    @Getter
    @Setter
    public static class Result {

        private Test test;

        private Set<Integer> markedAnswers = new HashSet<>();

        private Set<Answer> incorrectAnswers = new TreeSet<>((a, b) -> Integer.compare(a.getAnswerid(), b.getAnswerid()));

        private int correctQuestionsCount;

        private Set<Integer> correct = new HashSet<>();

        private static int calcCorrectQCount(List<Answer> incorrectAnswers, Test test, Set<Integer> correct) {
            test.getQuestions().forEach(e -> correct.add(e.getQuestionId()));
            incorrectAnswers.forEach(e -> correct.remove(e.getQuestion().getQuestionId()));
            return correct.size();
        }

        private Result(Test test, List<Answer> incorrectAnswers, List<Answer> markedAnswers) {
            this.incorrectAnswers.addAll(incorrectAnswers);
            markedAnswers.forEach(e -> this.markedAnswers.add(e.getAnswerid()));
            this.test = test;
            this.correctQuestionsCount = calcCorrectQCount(incorrectAnswers, test, correct);
        }

        public int getCorrectQuestionsCount() {
            return correctQuestionsCount;
        }

        public int getTotalQuestionsCount() {
            return test.getQuestions().size();
        }

        public String getPercentString() {
            double total = getTotalQuestionsCount() == 0 ? 1 : getTotalQuestionsCount();
            return String.format(Locale.US,"%3.1f", 100 * getCorrectQuestionsCount() / total);
        }

        public boolean isQuestionCorrect(int id) {
            return correct.contains(id);
        }

        public String getColor() {
            double total = getTotalQuestionsCount() == 0 ? 1 : getTotalQuestionsCount();
            double result = getCorrectQuestionsCount() / total;
            if (result < 0.5)
                return "red";
            if (result < 0.8)
                return "orange";
            return "green";
        }
    }

}
