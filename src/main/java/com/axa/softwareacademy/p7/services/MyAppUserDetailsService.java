package com.axa.softwareacademy.p7.services;

import com.axa.softwareacademy.p7.domain.User;
import com.axa.softwareacademy.p7.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * This service is aimed to verify user's permissions when login in
 */
@Service
public class MyAppUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    /**
     * @param userName is the username provided by user at front-end
     * @return the user's details on his permissions (enabled, ...)
     * @throws UsernameNotFoundException is thrown when given username is not found in database
     */
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(userName);
        GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole());
        UserDetails userDetails = (UserDetails)new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(), Arrays.asList(authority));
        return userDetails;
    }
}
