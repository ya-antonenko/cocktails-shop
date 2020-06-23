package com.cocktail.services;

import com.cocktail.domain.Usr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usr usrFromDB = userService.findByUsername(username);
        if (usrFromDB == null) throw new UsernameNotFoundException(username + "not found");
        List<SimpleGrantedAuthority> roles = Arrays.asList(new SimpleGrantedAuthority(usrFromDB.getRoles().toString()));
        return new User(usrFromDB.getUsername(), usrFromDB.getPassword(), roles);
    }
}
