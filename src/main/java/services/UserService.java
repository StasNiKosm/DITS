package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import repository.dao.entities.User;
import repository.managers.lazy.LazyManager;

@Service
public class UserService {

    private LazyManager<User> userLazyManager;

    @Autowired
    public void setUserLazyManager(LazyManager<User> userLazyManager) {
        this.userLazyManager = userLazyManager;
    }

    public User getUserFromLogin(String login) throws UsernameNotFoundException {
        return userLazyManager.executeSql("From User").get(0);
    };

}
