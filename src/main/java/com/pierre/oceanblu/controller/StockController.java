package com.pierre.oceanblu.controller;

import com.pierre.oceanblu.model.form.PurchaseProductsForm;
import com.pierre.oceanblu.service.MainService;
import com.pierre.oceanblu.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.unit.DataSize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/stock")
public class StockController {

    @Value("${spring.servlet.multipart.max-file-size}")
    private DataSize maxUploadSize;

    private final MainService mainService;
    private final UserService userService;

    @GetMapping
    public String showStock(Model model) {

        model.addAttribute("user", userService.getAuthenticatedUser());
        model.addAttribute("transactions", mainService.listAllTransactions());
        return "stock";
    }

    @GetMapping("/purchase-products")
    public String showPurchaseForm(Model model) {

        model.addAttribute("maxSize", maxUploadSize.toBytes());
        model.addAttribute("products", mainService.listAllProducts());
        model.addAttribute("user", userService.getAuthenticatedUser());
        model.addAttribute("form", new PurchaseProductsForm());
        return "purchase";
    }

    @PostMapping("/purchase-products")
    public String savePurchasedProducts(@ModelAttribute("form") PurchaseProductsForm form) throws IOException {

        mainService.purchaseProduct(form, userService.getAuthenticatedUser());
        return "redirect:/stock";
    }
}