package controllers.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import repository.dao.entities.Question;
import repository.dao.entities.Test;
import services.TestService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
        if (testId == null || !testService.isTestExist(testId))
            return new ModelAndView("redirect:/user/test-chooser?error");

        List<Question> questions =  new ArrayList<>(testService.getEagerInstance().getQuestionsFromTest(testId));
        if (questions.isEmpty())
            return new ModelAndView("redirect:/user/test-chooser?error");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/testPage");

        Test test = questions.iterator().next().getTest();
        modelAndView.addObject("testName", test.getName());
        modelAndView.addObject("testId", test.getTestId());

        Collections.shuffle(questions);
        modelAndView.addObject("questions", questions);

        return modelAndView;
    }

}
