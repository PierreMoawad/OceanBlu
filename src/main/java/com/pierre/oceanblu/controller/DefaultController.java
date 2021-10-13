package com.pierre.oceanblu.controller;

import com.pierre.oceanblu.model.User;
import com.pierre.oceanblu.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class DefaultController {

    private final UserService userService;

    @GetMapping("/")
    public String getIndex() {

        return "redirect:/products";
    }

    @GetMapping("/login")
    public String showLoginForm() {

        return "login";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {

        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user) {

        if (userService.isNewUser(user.getUsername())) {

            userService.registerUser(user);
            return "redirect:/login";
        }
        else {

            return "redirect:/register?error=true";
        }
    }
}