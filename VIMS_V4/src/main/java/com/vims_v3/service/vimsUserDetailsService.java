package com.vims_v3.service;

import com.vims_v3.model.User;
import com.vims_v3.repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * Created by promod on 12/1/2017.
 */
@Service
public class vimsUserDetailsService implements UserDetailsService {

    @Autowired
    private UsersRepo usersRepo;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException{
        User activeUser = usersRepo.findUserByUsername(userName);
        GrantedAuthority authority = new SimpleGrantedAuthority(activeUser.getRole());
        UserDetails userDetails = (UserDetails)new org.springframework.security.core.userdetails.User(activeUser.getName(),
                activeUser.getUserPassword(), Arrays.asList(authority));
        return userDetails;
    }


}
