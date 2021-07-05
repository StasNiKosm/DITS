package services.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import repository.dao.entities.User;
import repository.managers.lazy.impl.UserLazyManager;
import services.StatisticService;
import services.UserService;

public class DeleteUserWithHisStatisticResolver {

    StatisticService statisticService;

    UserService userService;


    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    @Autowired
    public void setStatisticService(StatisticService statisticService) {
        this.statisticService = statisticService;
    }


    public void deleteUserWithHisStatistic(User user) {
        statisticService.getLazyInstance().deleteStatisticByUser(user);
        userService.getLazyInstance().deleteUser(user);
    }


}
