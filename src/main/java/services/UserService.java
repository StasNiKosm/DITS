package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
        return userLazyManager.executeSql("FROM User where login = '" + login + "'")
                .stream()
                .filter(user -> user.getLogin().equals(login))
                .findFirst()
                .orElseThrow(() -> new UsernameNotFoundException("No user with name " + login));
    };

    public UserSecurityService.AuthorizedUser getPrincipal() {
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof  UserSecurityService.AuthorizedUser) {
            return (UserSecurityService.AuthorizedUser)principal;
        }
        return new UserSecurityService.AuthorizedUser(getUserFromLogin(principal.getUsername()), principal.getAuthorities());
    }

}
