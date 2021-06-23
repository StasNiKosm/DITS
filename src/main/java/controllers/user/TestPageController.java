package controllers.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import repository.dao.entities.Question;
import services.TestService;

import java.util.Set;

@Controller
public class TestPageController {

    TestService testService;

    @Autowired
    public void setTestService(TestService testService) {
        this.testService = testService;
    }

    @GetMapping("user/test")
    public ModelAndView testPage(@RequestParam(name = "testId", required = false) Integer testId) {
        System.out.println(testId);
        if (testId == null || !testService.isTestExist(testId))
            return new ModelAndView("redirect:/user/test-chooser?error");
        Set<Question> questions =  testService.getEagerInstance().getQuestionsFromTest(testId);
        System.out.println(questions);
        if (questions.isEmpty())
            return new ModelAndView("redirect:/user/test-chooser?error");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/testPage");
        modelAndView.addObject("testName", questions.iterator().next().getTest().getName());
        modelAndView.addObject("questions", questions);
        return modelAndView;
    }

    @PostMapping("user/test")
    public String resultPage() {
        return "redirect:/user/test-chooser";
    }

}
