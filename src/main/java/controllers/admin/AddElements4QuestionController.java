package controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import repository.dao.entities.Answer;
import repository.dao.entities.Link;
import repository.dao.entities.Literature;
import services.*;

@Controller
public class AddElements4QuestionController {

    private TestService testService;

    private QuestionService questionService;

    private LiteratureService literatureService;

    private LinkService linkService;

    private AnswerService answerService;

    @Autowired
    public void setQuestionService(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Autowired
    public void setLiteratureService(LiteratureService literatureService) {
        this.literatureService = literatureService;
    }

    @Autowired
    public void setLinkService(LinkService linkService) {
        this.linkService = linkService;
    }

    @Autowired
    public void setAnswerService(AnswerService answerService) {
        this.answerService = answerService;
    }

    @Autowired
    public void setTestService(TestService testService) {
        this.testService = testService;
    }


    @PostMapping(value = "/admin/addLiterature")
    public ModelAndView addLiterature(ModelAndView modelAndView, int testId, int questionId, int questionNumber,
                                      String literatureDescription, String link){

        System.out.println(literatureDescription);
        System.out.println(link);

        Literature literature = new Literature();
        literature.setDescription(literatureDescription);
        literature.setQuestion(questionService.getLazyInstance().getQuestionById(questionId));
        literatureService.getLazyInstance().createLiterature(literature);
        Link newLink = new Link();
        newLink.setLink(link);
        newLink.setLiterature(literature);
        linkService.getEagerInstance().createLink(newLink);

        TestManagementAdminController.config4EditingQuestionPage(modelAndView, testId, questionId, questionNumber, testService);
        return modelAndView;
    }

    @PostMapping(value = "/admin/addLinkToLiterature")
    public ModelAndView addLinkToLiterature(ModelAndView modelAndView, int testId, int questionId, int questionNumber,
                                      String link, int literatureId){

        System.out.println(link);
        System.out.println(literatureId);

        Link newLink = new Link();
        newLink.setLink(link);
        newLink.setLiterature(literatureService.getLazyInstance().getLiteratureById(literatureId));
        linkService.getEagerInstance().createLink(newLink);

        TestManagementAdminController.config4EditingQuestionPage(modelAndView, testId, questionId, questionNumber, testService);
        return modelAndView;
    }

    @PostMapping(value = "/admin/addAnswer")
    public ModelAndView addAnswer(ModelAndView modelAndView, int testId, int questionId, int questionNumber,
                                            String answerDescription,
                                  @RequestParam(value = "correct", required = false) String correct){

        System.out.println(answerDescription);
        System.out.println(correct);

        //correct == null if unchecked, == on if checked
        Answer answer = new Answer();
        answer.setQuestion(questionService.getLazyInstance().getQuestionById(questionId));
        answer.setDescription(answerDescription);
        if(correct == null){
            answer.setCorrect(0);
        } else {
            answer.setCorrect(1);
        }
        answerService.getLazyInstance().createAnswer(answer);
        TestManagementAdminController.config4EditingQuestionPage(modelAndView, testId, questionId, questionNumber, testService);
        return modelAndView;
    }

}

