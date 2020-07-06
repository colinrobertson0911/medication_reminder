package com.github.robbo1811.medication_reminder.security;


import com.github.robbo1811.medication_reminder.model.User;
import com.github.robbo1811.medication_reminder.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static org.springframework.security.core.userdetails.User.withUsername;

@Component
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserDao userDao;

    @Autowired
    JwtProvider jwtProvider;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException(String.format("User does not exist", username)));

        return withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities(user.getRoles())
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }

    /**
     * Extract username and roles from a validated jwt string.
     *
     * @param jwtToken jwt string
     * @return UserDetails if valid, Empty otherwise
     */
    public Optional<UserDetails> loadUserByJwtToken(String jwtToken){
        if (jwtProvider.isValidToken(jwtToken)) {
            return Optional.of(
                    withUsername(jwtProvider.getUsername(jwtToken))
                            .authorities(jwtProvider.getRoles(jwtToken))
                            .password("")//token does not have password but field may not be empty
                            .accountExpired(false)
                            .accountLocked(false)
                            .credentialsExpired(false)
                            .disabled(false)
                            .build());
        }
        return Optional.empty();
    }

    /**
     * Extract the username from the JWT then lookup the user in the database.
     *
     * @param jwtToken
     * @return
     */
    public Optional<UserDetails> loadUserByJwtTokenAndDatabase(String jwtToken){
        if (jwtProvider.isValidToken(jwtToken)) {
            return Optional.of(loadUserByUsername(jwtProvider.getUsername(jwtToken)));
        }else {
            return Optional.empty();
        }
    }
}
