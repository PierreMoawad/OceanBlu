package com.pierre.oceanblu.service;

import com.pierre.oceanblu.model.MyUserDetails;
import com.pierre.oceanblu.model.User;
import com.pierre.oceanblu.repository.UserRepository;
import com.pierre.oceanblu.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> user = userRepository.getUserByUsername(username);

        user.orElseThrow(() -> new UsernameNotFoundException("Username " + username + " not found"));

        return user.map(MyUserDetails::new).get();
    }

    public String authenticateUserAndGetJwt(String username, String password) {

        Optional<User> optionalUser;
        optionalUser = userRepository.getUserByUsername(username);
        optionalUser.orElseThrow(() -> new UsernameNotFoundException("Username " + username + " not found"));
        User user = optionalUser.get();
        String actualPassword = user.getPassword();

        if (!password.equals(actualPassword)) {

            throw new BadCredentialsException("Passwords don't match");
        }

        UserDetails userDetails = optionalUser.map(MyUserDetails::new).get();
        return jwtUtil.generateToken(userDetails);
    }
}
