package com.sku.fitizen.service;


import com.sku.fitizen.dto.CustomUserDetails;
import com.sku.fitizen.entity.FitizenUser;
import com.sku.fitizen.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService
{

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {

       FitizenUser user = userRepository.findByUserName(username);
        if(user != null) {




            return new CustomUserDetails(user);
        }

        return null;
    }



}
