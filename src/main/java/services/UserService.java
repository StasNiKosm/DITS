package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import repository.dao.entities.User;
import repository.managers.eager.EagerManager;
import repository.managers.lazy.LazyManager;

@Service
public class UserService {

    private UserServiceFacade lazyInstance;

    private UserServiceFacade eagerInstance;

    @Autowired
    public void setUserLazyManager(LazyManager<User> userLazyManager) {
        lazyInstance = new UserServiceFacade(userLazyManager);
    }

    @Autowired
    public void setUserEagerManager(EagerManager<User> userEagerManager) {
        eagerInstance = new UserServiceFacade(userEagerManager);
    }

    public UserServiceFacade getEagerInstance() {
        return eagerInstance;
    }

    public UserServiceFacade getLazyInstance() {
        return lazyInstance;
    }

    public static class UserServiceFacade {

        private final LazyManager<User> manager;

        public UserServiceFacade(LazyManager<User> manager) {
            this.manager = manager;
        }

        public User getUserById(int id) {
            return manager.read(id);
        }

        public User getUserByLogin(String login) throws UsernameNotFoundException {
            return manager.executeSql("FROM User where login = '" + login + "'")
                    .stream()
                    .filter(user -> user.getLogin().equals(login))
                    .findFirst()
                    .orElseThrow(() -> new UsernameNotFoundException("No user with name '" + login + "'"));
        };

    }

    public boolean isLoginRegistered(String login) {
        try {
            getLazyInstance().getUserByLogin(login);
            return true;
        } catch (UsernameNotFoundException ex) {
            return false;
        }
    }

    public UserSecurityService.AuthorizedUser getUserFromSession() {
        UserDetails principal = (UserDetails) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();

        if (principal instanceof  UserSecurityService.AuthorizedUser) {
            return (UserSecurityService.AuthorizedUser)principal;
        }

        return new UserSecurityService.AuthorizedUser(
                getLazyInstance().getUserByLogin(principal.getUsername()),
                principal.getAuthorities()
        );
    }

}
