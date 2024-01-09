package com.ravekidd.v1.service.services;

import com.ravekidd.v1.exception.ServerException;
import com.ravekidd.v1.model.Role;
import com.ravekidd.v1.model.User;
import com.ravekidd.v1.service.helpers.ActionHelper;
import com.ravekidd.v1.service.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Custom implementation of Spring Security's UserDetailsService.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;
    private ActionHelper actionHelper;

    /**
     * Constructor for CustomUserDetailsService.
     *
     * @param userRepository The repository for managing user data.
     * @param actionHelper   Helper class for performing common actions.
     */
    @Autowired
    public CustomUserDetailsService(UserRepository userRepository, ActionHelper actionHelper) {
        this.userRepository = userRepository;
        this.actionHelper = actionHelper;
    }

    /**
     * Loads a user by their username.
     *
     * @param username The username of the user to load.
     * @return UserDetails object representing the loaded user.
     * @throws UsernameNotFoundException if the user is not found.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            User user = actionHelper.findUserByUsername(username, userRepository);
            return new org.springframework.security.core.userdetails.User(
                    user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
        } catch (ServerException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Maps a list of Role objects to a list of GrantedAuthority objects.
     *
     * @param roles The list of Role objects to be mapped.
     * @return The list of GrantedAuthority objects mapped from the Role objects.
     */
    private Collection<GrantedAuthority> mapRolesToAuthorities(List<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
