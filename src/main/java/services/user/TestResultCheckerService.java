package services.user;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.dao.entities.Answer;
import repository.dao.entities.Literature;
import repository.dao.entities.Question;
import repository.dao.entities.Test;
import services.AnswerService;
import services.LiteratureService;
import services.QuestionService;
import services.UserSecurityService;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TestResultCheckerService {

    AnswerService answerService;

    QuestionService questionService;

    LiteratureService literatureService;

    @Autowired
    public void setAnswerService(AnswerService answerService) {
        this.answerService = answerService;
    }

    @Autowired
    public void setQuestionService(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Autowired
    public void setLiteratureService(LiteratureService literatureService) {
        this.literatureService = literatureService;
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
           if (answer.getCorrect() == 0)
               incorrectAnswers.add(answer);
           if (answer.getCorrect() == 1)
               correctAnswer.remove(answer);
        });

        incorrectAnswers.addAll(correctAnswer);

        Set<Literature> literature = new HashSet<>();
        incorrectAnswers.forEach(answer -> literature.addAll(
                literatureService.getEagerInstance().getLiteratureByQuestionId(answer.getQuestion().getQuestionId())
            )
        );

        System.out.println(correctAnswer);

        return new Result(incorrectAnswers, new ArrayList<>(literature));
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
    public static class Result {

        List<Answer> incorrectAnswers;

        List<Literature> literature;

        private Result(List<Answer> incorrectAnswers, List<Literature> literature) {
            this.incorrectAnswers = incorrectAnswers;
            this.literature = literature;
        }

    }

}
