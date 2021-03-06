package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import providers.AppContextProvider;
import repository.dao.emuns.RoleEnum;
import repository.dao.entities.User;
import repository.managers.eager.EagerManager;
import repository.managers.lazy.LazyManager;

import java.util.List;

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

        private UserServiceFacade(LazyManager<User> manager) {
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
        }

        public void updateUser(User user){
            manager.update(user);
        }

        //Не удалит юзера, если у него уже есть кака-либо статисти - упадет с ошибкой (иди в статистик сервис)
        public void deleteUser(User user){
            manager.delete(user);
        }

        public boolean containsUserById(int userId){
            return this.manager.read(userId) != null;
        }

    }

    public boolean isLoginRegistered(String login) {
        try {
            getLazyInstance().getUserByLogin(login);
            return true;
        } catch (UsernameNotFoundException ex) {
            return false;
        }
    }

    public void registerNewUser(String login, String firstName, String lastName, String password, RoleEnum role) {
        User user = AppContextProvider.getAppContext().getBean(User.class);

        user.setLogin(login);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPassword(password);
        user.setRole(role.getName());

        getLazyInstance().manager.create(user);
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

    public List<User> getAllUserWithoutSessionUser(){
        List<User> users = lazyInstance.manager.getAll();
        User userSession = this.getUserFromSession().getUser();
        for (int i = 0; i < users.size(); i++) {
            if(users.get(i).getUserId() == userSession.getUserId()){
                users.remove(i);
                break;
            }
        }
        return users;
    }

}
