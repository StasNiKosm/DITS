package controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import services.StatisticService;

@Controller
public class StatisticAdminController {

    private StatisticService statisticService;

    @Autowired
    public void setStatisticService(StatisticService statisticService) {
        this.statisticService = statisticService;
    }

    @GetMapping(value = "/admin/question_statistics")
    public ModelAndView getQuestionStatistics(ModelAndView modelAndView){
        modelAndView.setViewName("admin/questionStatistics");
        modelAndView.addObject("statistics", statisticService.getLazyInstance().getQuestionStatistics());
        return modelAndView;
    }

    @GetMapping(value = "/admin/test_statistics")
    public ModelAndView getTestStatistics(ModelAndView modelAndView){
        modelAndView.setViewName("admin/testStatistics");
        modelAndView.addObject("statistics", statisticService.getLazyInstance().getTestStatistics());
        return modelAndView;
    }

    @GetMapping(value = "/admin/user_statistics")
    public ModelAndView getUserStatistics(ModelAndView modelAndView){
        modelAndView.setViewName("admin/userStatistics");
        modelAndView.addObject("statistics", statisticService.getLazyInstance().getUserStatistics());
        return modelAndView;
    }
}
