package com.pierre.oceanblu.controller;

import com.pierre.oceanblu.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public String showUserProfile(@PathVariable("id") Long id, Model model) {

        model.addAttribute("purchases", userService.listAllPurchases(id));
        model.addAttribute("user", userService.getUserByID(id));
        return "user-profile";
    }

    @GetMapping("/{id}/wishlist")
    public String showUserWishlist(@PathVariable("id") Long id, Model model) {

        model.addAttribute("products", userService.getUserByID(id).getProductsInWishlist());
        model.addAttribute("user", userService.getUserByID(id));
        return "wishlist";
    }
}