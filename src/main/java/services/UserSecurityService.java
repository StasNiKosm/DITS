package services;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserSecurityService implements UserDetailsService {

    private UserService userService;

    private static PasswordEncoder passwordEncoder;

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        UserSecurityService.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    private List<GrantedAuthority> buildUserAuthority(String role) {
        Set<GrantedAuthority> setAuths = new HashSet<>();
        setAuths.add(new SimpleGrantedAuthority(role));
        return new ArrayList<>(setAuths);
    }

    private AuthorizedUser buildUserForAuthentication(repository.dao.entities.User user, List<GrantedAuthority> authorities) {
        System.out.println("try login " + user);
        return new AuthorizedUser(user, authorities);
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        System.out.println("try login " + login);
        repository.dao.entities.User user = userService.getLazyInstance().getUserByLogin(login);
        List<GrantedAuthority> authorities = buildUserAuthority(user.getRole());
        return buildUserForAuthentication(user, authorities);
    }

    @Getter
    @Setter
    public static class AuthorizedUser extends User {

        private final repository.dao.entities.User user;

        public AuthorizedUser(repository.dao.entities.User user, Collection<? extends GrantedAuthority> authorities) {
            super(user.getLogin(), passwordEncoder.encode(user.getPassword()), authorities);
            this.user = user;
        }

    }

}