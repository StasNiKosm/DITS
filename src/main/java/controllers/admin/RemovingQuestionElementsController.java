package controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import repository.dao.entities.Question;
import repository.dao.entities.Test;
import services.*;

@Controller
public class RemovingQuestionElementsController {

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


    @PostMapping(value = "/admin/deleteLink")
    public ModelAndView deleteLink(ModelAndView modelAndView, int testId, int questionId, int questionNumber, int id){

        if(linkService.getLazyInstance().containsLinkById(id)) {
            linkService.getEagerInstance().deleteLinkById(id);
        }

        TestManagementAdminController.config4EditingQuestionPage(modelAndView, testId, questionId, questionNumber, testService);
        return modelAndView;
    }

    @PostMapping(value = "/admin/deleteLiterature")
    public ModelAndView deleteLiterature(ModelAndView modelAndView, int testId, int questionId, int questionNumber, int id){

        if(literatureService.getLazyInstance().containsLiteratureById(id)) {
            literatureService.getEagerInstance().deleteLiteratureById(id);
        }

        TestManagementAdminController.config4EditingQuestionPage(modelAndView, testId, questionId, questionNumber, testService);
        return modelAndView;
    }

    @PostMapping(value = "/admin/deleteAnswer")
    public ModelAndView deleteAnswer(ModelAndView modelAndView, int testId, int questionId, int questionNumber, int id){

        if(answerService.getLazyInstance().containsAnswerById(id)) {
            answerService.getEagerInstance().deleteAnswerById(id);
        }

        TestManagementAdminController.config4EditingQuestionPage(modelAndView, testId, questionId, questionNumber, testService);
        return modelAndView;
    }

    @PostMapping(value = "/admin/deleteQuestion")
    public ModelAndView deleteQuestion(ModelAndView modelAndView, int testId, int questionId, int questionNumber, int id){

        if(questionService.getLazyInstance().containsQuestionById(id)) {
            questionService.getEagerInstance().deleteQuestionById(id);
        }

        Test test = testService.getEagerInstance().getTestById(testId);

        if(test.getQuestions().size() == 0){
            modelAndView.setViewName("admin/emptyTestPage");
            modelAndView.addObject("test", test);
        } else {
            TestManagementAdminController.config4EditingQuestionPage(modelAndView, testId, test.getQuestions().stream().findFirst().get().getQuestionId(), questionNumber, testService);
        }
        return modelAndView;
    }

}
