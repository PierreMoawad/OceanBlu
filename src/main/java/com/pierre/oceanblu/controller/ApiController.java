package com.pierre.oceanblu.controller;

import com.pierre.oceanblu.model.AuthenticationRequest;
import com.pierre.oceanblu.model.AuthenticationResponse;
import com.pierre.oceanblu.model.Transaction;
import com.pierre.oceanblu.service.MainService;
import com.pierre.oceanblu.service.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ApiController {

    private final MainService mainService;
    private final MyUserDetailsService userDetailsService;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> createAuthenticationToken(
            @RequestBody AuthenticationRequest request) {

        String jwt = userDetailsService.authenticateUserAndGetJwt(request.getUsername(), request.getPassword());
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @GetMapping("/stock")
    public ResponseEntity<String[]> getStock() {

        List<Transaction> transactions = mainService.listAllTransactions();
        return ResponseEntity.ok(transactions.stream().map(Objects::toString).toArray(String[]::new));
    }
}
