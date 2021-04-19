package com.pierre.oceanblu.service;

import com.pierre.oceanblu.model.Product;
import com.pierre.oceanblu.model.Transaction;
import com.pierre.oceanblu.model.User;
import com.pierre.oceanblu.model.Wish;
import com.pierre.oceanblu.model.form.ChangePasswordForm;
import com.pierre.oceanblu.model.form.EditUserProfileForm;
import com.pierre.oceanblu.repository.UserRepository;
import com.pierre.oceanblu.repository.WishRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.pierre.oceanblu.model.Transaction.Code.*;
import static com.pierre.oceanblu.model.User.Role.USER;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final WishRepository wishRepository;
    private final PasswordEncoder encoder;

    public void registerUser(User user) {

        user.setActive(true);
        user.setRole(USER);
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public User getAuthenticatedUser() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<User> optionalUser = userRepository.getUserByUsername(username);
        optionalUser.orElseThrow(() -> new UsernameNotFoundException("Username " + username + " not found"));
        return optionalUser.get();
    }

    public Collection<Transaction> listAllPurchases(Long id) {

        return userRepository.getById(id).getTransactions().stream()
                .filter(t -> t.getCode().equals(OUT))
                .collect(Collectors.toList());
    }

    public boolean isNewUser(String username) {

        return !userRepository.findAll().stream()
                .map(User::getUsername)
                .collect(Collectors.toList())
                .contains(username);
    }

    public User getUserByID(Long id) {

        return userRepository.getById(id);
    }

    public boolean changePassword(ChangePasswordForm form) {

        User user = userRepository.getById(form.getUserId());

        if (encoder.matches(form.getOldPassword(), user.getPassword())) {

            user.setPassword(encoder.encode(form.getNewPassword()));
            userRepository.save(user);
            return true;
        }

        return false;
    }

    public void updateUser(EditUserProfileForm form) {

        User user = userRepository.getById(form.getUserId());
        user.setFirstName(form.getFirstName());
        user.setLastName(form.getLastName());
        user.setUsername(form.getUsername());
        user.setGender(form.getGender());
        userRepository.save(user);
    }

    public boolean addProductToWishlist(User user, Product product) {

        if (!wishRepository.existsByUserAndProduct(user, product)) {

            wishRepository.save(Wish.builder()
                    .user(user)
                    .product(product)
                    .build());

            return true;
        }

        return false;
    }

    public boolean removeProductFromWishlist(User user, Product product) {

        if (wishRepository.existsByUserAndProduct(user, product)) {

            wishRepository.delete(wishRepository.getByUserAndProduct(user, product));
            return true;
        }

        return false;
    }

    public boolean isProductInWishlist(User user, Product product) {

        return wishRepository.existsByUserAndProduct(user, product);
    }
}
