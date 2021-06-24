package controllers.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import repository.dao.entities.Test;
import services.TestService;
import services.UserService;
import services.user.TestResultCheckerService;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
public class ResultPageController {

    private TestService testService;

    private TestResultCheckerService testResultCheckerService;

    private UserService userService;

    @Autowired
    public void setTestService(TestService testService) {
        this.testService = testService;
    }

    @Autowired
    public void setTestResultCheckerService(TestResultCheckerService testResultCheckerService) {
        this.testResultCheckerService = testResultCheckerService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("user/test")
    public ModelAndView resultPage(@RequestParam("testId") Integer testId, HttpServletRequest request) {
        System.out.println(request.getParameterMap());
        Test test = testService.getEagerInstance().getTestById(testId);
        TestResultCheckerService.Result result = testResultCheckerService.createReport(request.getParameterMap(), test, userService.getUserFromSession(), new Date());
        return new ModelAndView("user/resultPage", "result", result);
    }

}
