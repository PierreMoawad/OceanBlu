package com.pierre.oceanblu.controller;

import com.pierre.oceanblu.model.form.ChangePasswordForm;
import com.pierre.oceanblu.model.form.EditUserProfileForm;
import com.pierre.oceanblu.model.form.RatingForm;
import com.pierre.oceanblu.service.MainService;
import com.pierre.oceanblu.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;

import static com.pierre.oceanblu.model.form.RatingForm.Type.*;

@Controller
@AllArgsConstructor
@RequestMapping("/modals")
public class ModalController {

    private final MainService mainService;
    private final UserService userService;

    @GetMapping("/review-modal")
    public String showReviewModal(@RequestParam("transactionId") Long transactionId, Model model) {

        model.addAttribute("form", new RatingForm(transactionId));
        return "review-modal";
    }

    @PostMapping("/review-modal")
    public String saveReview(@ModelAttribute("form") RatingForm form) {

        form.setType(ONLY_REVIEW);
        mainService.saveRating(form);
        return "redirect:/users/" + userService.getAuthenticatedUser().getId();
    }

    @GetMapping("/rate-and-review-modal")
    public String showRateAndReviewModal(@RequestParam("transactionId") Long transactionId, Model model) {

        model.addAttribute("form", new RatingForm(transactionId));
        return "rate-and-review-modal";
    }

    @PostMapping("/rate-and-review-modal")
    public String saveRating(@ModelAttribute("form") RatingForm form) {

        form.setType(RATE_AND_REVIEW);
        mainService.saveRating(form);
        return "redirect:/users/" + userService.getAuthenticatedUser().getId();
    }

    @GetMapping("/edit-profile-modal")
    public String showEditProfileForm(@RequestParam("userId") Long userId, Model model) {

        model.addAttribute("form", new EditUserProfileForm(userService.getUserByID(userId)));
        return "edit-profile-modal";
    }

    @PostMapping("/edit-profile-modal")
    public String editProfile(@ModelAttribute("form") EditUserProfileForm form) {

        if (form.getUsername().equals(userService.getUserByID(form.getUserId()).getUsername()) ||
            userService.isNewUser(form.getUsername())) {

            userService.updateUser(form);
            return "redirect:/users/" + form.getUserId();
        }

        return MessageFormat.format("redirect:/users/{0}?userExists=true", form.getUserId());
    }

    @GetMapping("/change-password-modal")
    public String showChangePasswordForm(@RequestParam("userId") Long userId, Model model) {

        model.addAttribute("form", new ChangePasswordForm(userId));
        return "change-password-modal";
    }

    @PostMapping("/change-password-modal")
    public String changePassword(@ModelAttribute("form") ChangePasswordForm form) {

        if (userService.changePassword(form)) {

            return "redirect:/logout?newPass=true";
        }

        return MessageFormat.format("redirect:/users/{0}?wrongPass=true", form.getUserId());
    }
}
