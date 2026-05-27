package com.taskflow.taskmanager.security;

import com.taskflow.taskmanager.entity.User;
import com.taskflow.taskmanager.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            return userService.findByEmail(username)
                    .orElseThrow(()-> new RuntimeException("User not found"));
    }
}
