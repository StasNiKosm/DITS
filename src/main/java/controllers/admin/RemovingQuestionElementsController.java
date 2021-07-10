package controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import repository.dao.entities.Question;
import repository.dao.entities.Test;
import services.TestService;

@Controller
public class RemovingQuestionElementsController {

    private TestService testService;

    @Autowired
    public void setTestService(TestService testService) {
        this.testService = testService;
    }


    @PostMapping(value = "/admin/deleteLink")
    public ModelAndView deleteLink(ModelAndView modelAndView, int testId, int questionId, int questionNumber, int id){
        TestManagementAdminController.config4EditingQuestionPage(modelAndView, testId, questionId, questionNumber, testService);

        System.out.println(id);

        return modelAndView;
    }

    @PostMapping(value = "/admin/deleteLiterature")
    public ModelAndView deleteLiterature(ModelAndView modelAndView, int testId, int questionId, int questionNumber, int id){
        TestManagementAdminController.config4EditingQuestionPage(modelAndView, testId, questionId, questionNumber, testService);

        System.out.println(id);

        return modelAndView;
    }

    @PostMapping(value = "/admin/deleteAnswer")
    public ModelAndView deleteAnswer(ModelAndView modelAndView, int testId, int questionId, int questionNumber, int id){
        TestManagementAdminController.config4EditingQuestionPage(modelAndView, testId, questionId, questionNumber, testService);

        System.out.println(id);

        return modelAndView;
    }

    @PostMapping(value = "/admin/deleteQuestion")
    public ModelAndView deleteQuestion(ModelAndView modelAndView, int testId, int questionId, int questionNumber, int id){

        //FIXME проверка, а осталось ли вопросов вообще? если нет то вернуть старниуу ао заполнению первого нового :)
        //FIXME questionId??? questionNumber???
        TestManagementAdminController.config4EditingQuestionPage(modelAndView, testId, questionId, questionNumber, testService);

        Test test = testService.getEagerInstance().getTestById(testId);
        if(test.getQuestions().size() == 0){
            modelAndView.setViewName("admin/emptyTestPage");
        }

        System.out.println(id);

        return modelAndView;
    }

}
