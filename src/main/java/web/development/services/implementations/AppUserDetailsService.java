package web.development.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import web.development.repositories.UserRepository;



import java.util.Collections;


public class AppUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;


    public AppUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(u -> new User(
                        u.getUsername(),
                        u.getPassword(), Collections.singleton((new SimpleGrantedAuthority("ROLE_" + u.getRole().getRole()))))).orElseThrow(() -> new UsernameNotFoundException(username + " was not found!"));




    }
}
