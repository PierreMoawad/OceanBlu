package com.pierre.oceanblu.service;

import com.pierre.oceanblu.model.MyUserDetails;
import com.pierre.oceanblu.model.User;
import com.pierre.oceanblu.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> user = userRepository.getUserByUsername(username);

        user.orElseThrow(() -> new UsernameNotFoundException("Username " + username + " not found"));

        return user.map(MyUserDetails::new).get();
    }
}
