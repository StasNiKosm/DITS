package controllers.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.StatisticService;
import services.UserSecurityService;
import services.UserService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class StatisticPageController {

    private final int onPageCount = 20;

    private StatisticService statisticService;

    private UserService userService;

    @Autowired
    public void setStatisticService(StatisticService statisticService) {
        this.statisticService = statisticService;
    }

    @Autowired
    public void setUserSecurityService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/statistic")
    public ModelAndView showPage(ModelAndView modelAndView, @RequestParam(value = "page", defaultValue = "1") int page) {

        if (page < 0)
            page = 0;

        UserSecurityService.AuthorizedUser user = userService.getUserFromSession();

        List<Integer> pages = new ArrayList<>();
        if (page != 0)
            pages.addAll(Arrays.asList(page - 1, page, page + 1, page + 2, page + 3));
        else
            pages.addAll(Arrays.asList(page, page + 1, page + 2, page + 3, page + 4));

        modelAndView.setViewName("user/ownStatistic");
        modelAndView.addObject("statistic", statisticService.getEagerInstance().getStatisticFromUser(user, page, onPageCount));
        modelAndView.addObject("user", user.getUser());
        modelAndView.addObject("pages", pages);
        modelAndView.addObject("cPage", page);

        return modelAndView;
    }

}
