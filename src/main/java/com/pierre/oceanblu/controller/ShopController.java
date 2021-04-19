package com.pierre.oceanblu.controller;

import com.pierre.oceanblu.service.MainService;
import com.pierre.oceanblu.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("/products")
public class ShopController {

    private final MainService mainService;
    private final UserService userService;

    @GetMapping
    public String showShop(Model model) {

        model.addAttribute("user", userService.getAuthenticatedUser());
        model.addAttribute("products", mainService.listAllProducts());
        return "products";
    }

    @GetMapping("/{id}")
    public String showProduct(@PathVariable("id") Long id, Model model) {

        model.addAttribute("user", userService.getAuthenticatedUser());
        model.addAttribute("product", mainService.getProductByID(id));
        return "product";
    }

    @PostMapping("/{id}")
    public String sellProduct(@PathVariable("id") Long id, @RequestParam("quantity") Integer quantity) {

        mainService.sellProduct(id, userService.getAuthenticatedUser(), quantity);
        userService.removeProductFromWishlist(userService.getAuthenticatedUser(), mainService.getProductByID(id));
        return "redirect:/products/{id}/thanks";
    }

    @GetMapping("/{id}/thanks")
    public String thanksForPurchasing(@PathVariable("id") Long id, Model model) {

        model.addAttribute("user", userService.getAuthenticatedUser());
        model.addAttribute("product", mainService.getProductByID(id));
        return "thanks";
    }

    @GetMapping("/{id}/product-in-wishlist")
    public ResponseEntity<Boolean> isProductInWishlist(@PathVariable("id") Long id) {

        return ResponseEntity.ok()
                .body(userService.isProductInWishlist(
                        userService.getAuthenticatedUser(),
                        mainService.getProductByID(id)
                ));
    }

    @PutMapping("/{id}/add-to-wishlist")
    public ResponseEntity<String> addProductToWishlist(@PathVariable("id") Long id) {

        userService.addProductToWishlist(userService.getAuthenticatedUser(), mainService.getProductByID(id));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}/remove-from-wishlist")
    public ResponseEntity<String> removeProductFromWishlist(@PathVariable("id") Long id) {

        userService.removeProductFromWishlist(userService.getAuthenticatedUser(), mainService.getProductByID(id));
        return ResponseEntity.ok().build();
    }
}