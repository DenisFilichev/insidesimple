package com.example.insideapp.security;

import com.example.insideapp.data.UserService;
import com.example.insideapp.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Implementation of {@link UserDetailsService}.
 *
 * @author Denis Filichev
 * @version 1.0
 */

@Service
public class JwtUserDetailService implements UserDetailsService {

    private final UserService userService;


    @Autowired
    public JwtUserDetailService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User appUser = userService.getUserByUsername(username);
        if(appUser!=null){
            return new JwtUserDetails(appUser);
        }else throw new UsernameNotFoundException(username + " not found!");
    }
}
