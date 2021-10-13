package com.pierre.oceanblu.controller;

import com.pierre.oceanblu.model.AuthenticationRequest;
import com.pierre.oceanblu.model.AuthenticationResponse;
import com.pierre.oceanblu.model.Transaction;
import com.pierre.oceanblu.security.JwtUtil;
import com.pierre.oceanblu.service.MainService;
import com.pierre.oceanblu.service.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ApiController {

    private final MainService mainService;
    private final AuthenticationManager authenticationManager;
    private final MyUserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> createAuthenticationToken(
            @RequestBody AuthenticationRequest request) throws Exception {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
        } catch (BadCredentialsException e) {

            throw new Exception(e);
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @GetMapping("/stock")
    public ResponseEntity<List<Transaction>> getStock() {

        List<Transaction> transactions = mainService.listAllTransactions();
        return ResponseEntity.ok(transactions);
    }
}
