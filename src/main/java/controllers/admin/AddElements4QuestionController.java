package controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.TestService;

@Controller
public class AddElements4QuestionController {

    private TestService testService;

    @Autowired
    public void setTestService(TestService testService) {
        this.testService = testService;
    }

    @PostMapping(value = "/admin/addLiterature")
    public ModelAndView addLiterature(ModelAndView modelAndView, int testId, int questionId, int questionNumber,
                                      String literatureDescription, String link){

        System.out.println(literatureDescription);
        System.out.println(link);

        //creating & add

        TestManagementAdminController.config4EditingQuestionPage(modelAndView, testId, questionId, questionNumber, testService);
        return modelAndView;
    }

    @PostMapping(value = "/admin/addLinkToLiterature")
    public ModelAndView addLinkToLiterature(ModelAndView modelAndView, int testId, int questionId, int questionNumber,
                                      String link, int literatureId){

        System.out.println(link);
        System.out.println(literatureId);

        //creating & add

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

        //creating & add

        TestManagementAdminController.config4EditingQuestionPage(modelAndView, testId, questionId, questionNumber, testService);
        return modelAndView;
    }

}
