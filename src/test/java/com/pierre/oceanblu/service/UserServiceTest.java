package com.pierre.oceanblu.service;

import com.pierre.oceanblu.model.*;
import com.pierre.oceanblu.model.form.EditUserProfileForm;
import com.pierre.oceanblu.repository.*;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.*;

import static com.pierre.oceanblu.model.Transaction.Code.*;
import static com.pierre.oceanblu.model.User.*;
import static com.pierre.oceanblu.model.User.Gender.*;
import static com.pierre.oceanblu.model.User.Role.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    private final UserRepository userRepositoryMock = mock(UserRepository.class);
    private final WishRepository wishRepositoryMock = mock(WishRepository.class);
    private final PasswordEncoder encoderMock = mock(PasswordEncoder.class);

    private UserService userService;

    @Before
    public void before() {

        userService = new UserService(
                userRepositoryMock,
                wishRepositoryMock,
                encoderMock
        );
    }

    ///////////////////////////////////////
    //  Tests for registerUser() method  //
    ///////////////////////////////////////

    // Test if method save() is called
    @Test
    public void saveIsCalledTest() {

        User user = User.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .username("j.doe")
                .password("ol1124#")
                .role(USER)
                .gender(MALE)
                .build();

        when(userRepositoryMock.save(user)).thenReturn(user);

        userService.registerUser(user);

        verify(userRepositoryMock, times(1)).save(user);
    }

    // Test if user is set to active and role to USER
    @Test
    public void userActiveAndRoleUserTest() {

        final boolean actualActive = true;
        @SuppressWarnings("Necessary for clarity") final Role actualRole = USER;

        User user = User.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .username("j.doe")
                .password("ol1124#")
                .gender(MALE)
                .build();

        when(userRepositoryMock.save(user)).thenReturn(user);

        userService.registerUser(user);
        boolean expectedActive = userRepositoryMock.save(user).isActive();
        Role expectedRole = userRepositoryMock.save(user).getRole();

        assertThat(actualActive).isEqualTo(expectedActive);
        assertThat(actualRole).isEqualTo(expectedRole);
    }

    // End of tests for registerUser() ///////////////

    @Test
    public void listAllPurchasesTest() {

        final Long userId = 1L;

        Transaction transactionOut = Transaction.builder()
                .id(1L)
                .product(Product.builder()
                        .id(1000L)
                        .name("iPhone X")
                        .company("Apple")
                        .price(950F)
                        .quantity(14)
                        .description("Nice Phone!")
                        .build())
                .date(LocalDateTime.of(2021, 2, 12, 3, 27, 16))
                .code(OUT)
                .items(2)
                .user(User.builder()
                        .id(userId)
                        .firstName("John")
                        .lastName("Doe")
                        .username("j.doe")
                        .password("ol1124#")
                        .role(USER)
                        .gender(MALE)
                        .build())
                .rating(Rating.builder()
                        .id(1L)
                        .rate(4)
                        .reviewLeft(false)
                        .build())
                .currentItems(12)
                .currentValue(11400F)
                .build();

        Transaction transactionIn = Transaction.builder()
                .id(2L)
                .product(Product.builder()
                        .id(1001L)
                        .name("Galaxy S9")
                        .company("Samsung")
                        .price(1000F)
                        .quantity(3)
                        .description("Some specs...")
                        .build())
                .date(LocalDateTime.of(2021, 3, 4, 12, 5, 37))
                .code(IN)
                .items(12)
                .user(User.builder()
                        .id(userId)
                        .firstName("John")
                        .lastName("Doe")
                        .username("j.doe")
                        .password("ol1124#")
                        .role(USER)
                        .gender(MALE)
                        .build())
                .currentItems(15)
                .currentValue(15000F)
                .build();

        User user = User.builder()
                .id(userId)
                .firstName("John")
                .lastName("Doe")
                .username("j.doe")
                .password("ol1124#")
                .role(USER)
                .gender(MALE)
                .transactions(Arrays.asList(transactionOut, transactionIn))
                .build();

        when(userRepositoryMock.getById(userId)).thenReturn(user);
        Collection<Transaction> actualTransactions = Collections.singletonList(transactionOut);
        Collection<Transaction> expectedTransactions = userService.listAllPurchases(userId);

        assertThat(actualTransactions).isEqualTo(expectedTransactions);
    }

    @Test
    public void isNewUserTest() {

        final String newUsername = "samo";

        List<User> users = Arrays.asList(

                User.builder()
                        .id(1L)
                        .firstName("John")
                        .lastName("Doe")
                        .username("j.doe")
                        .password("ol1124#")
                        .role(USER)
                        .gender(MALE)
                        .build(),

                User.builder()
                        .id(2L)
                        .firstName("Caroline")
                        .lastName("Thomson")
                        .username("admin")
                        .password("aDmIn*/8558/*")
                        .role(ADMIN)
                        .gender(FEMALE)
                        .build()
        );

        when(userRepositoryMock.findAll()).thenReturn(users);

        boolean expectedNewUser = userService.isNewUser(newUsername);

        assertTrue(expectedNewUser);
    }

    @Test
    public void isNotNewUserTest() {

        final String newUsername = "admin";

        List<User> users = Arrays.asList(

                User.builder()
                        .id(1L)
                        .firstName("John")
                        .lastName("Doe")
                        .username("j.doe")
                        .password("ol1124#")
                        .role(USER)
                        .gender(MALE)
                        .build(),

                User.builder()
                        .id(2L)
                        .firstName("Caroline")
                        .lastName("Thomson")
                        .username("admin")
                        .password("aDmIn*/8558/*")
                        .role(ADMIN)
                        .gender(FEMALE)
                        .build()
        );

        when(userRepositoryMock.findAll()).thenReturn(users);

        boolean expectedNewUser = userService.isNewUser(newUsername);

        assertFalse(expectedNewUser);
    }

    @Test
    public void getByIdTest() {

        final Long userId = 1L;

        User actualUser = User.builder()
                .id(userId)
                .firstName("John")
                .lastName("Doe")
                .username("j.doe")
                .password("ol1124#")
                .role(USER)
                .gender(MALE)
                .build();

        when(userRepositoryMock.getById(userId)).thenReturn(actualUser);

        User expectedUser = userService.getUserByID(userId);

        assertThat(actualUser).isEqualTo(expectedUser);
    }

    @Test
    public void updateUserTest() {

        final Long userId = 1L;
        final String firstName = "John";
        final String lastName = "Doe";
        final String username = "j.doe";
        @SuppressWarnings("Necessary for testing") final Gender gender = MALE;

        User actualUpdatedUser = User.builder()
                .id(userId)
                .firstName("Caroline")
                .lastName("Thomson")
                .username("admin")
                .password("aDmIn*/8558/*")
                .role(ADMIN)
                .gender(FEMALE)
                .build();

        EditUserProfileForm form = EditUserProfileForm.builder()
                .userId(userId)
                .firstName(firstName)
                .lastName(lastName)
                .username(username)
                .gender(gender)
                .build();

        when(userRepositoryMock.getById(userId)).thenReturn(actualUpdatedUser);
        when(userRepositoryMock.save(actualUpdatedUser)).thenReturn(actualUpdatedUser);

        userService.updateUser(form);
        User expectedUpdatedUser = userRepositoryMock.getById(userId);

        assertThat(actualUpdatedUser).isEqualTo(expectedUpdatedUser);
    }

    @Test
    public void productIsAddedToWishlistIfNotExistsTest() {

        User user = User.builder()
                .id(1L)
                .firstName("Caroline")
                .lastName("Thomson")
                .username("admin")
                .password("aDmIn*/8558/*")
                .role(ADMIN)
                .gender(FEMALE)
                .build();

        Product product = Product.builder()
                .id(1002L)
                .name("iPhone 12")
                .company("Apple")
                .price(1200F)
                .quantity(5)
                .description("Some specs...!")
                .build();

        Wish wish = Wish.builder()
                .product(product)
                .user(user)
                .build();

        when(wishRepositoryMock.existsByUserAndProduct(user, product)).thenReturn(false);
        when(wishRepositoryMock.save(wish)).thenReturn(wish);

        boolean isProductAdded = userService.addProductToWishlist(user, product);

        assertTrue(isProductAdded);
    }

    @Test
    public void productIsNotAddedToWishlistIfExistsTest() {

        User user = User.builder()
                .id(1L)
                .firstName("Caroline")
                .lastName("Thomson")
                .username("admin")
                .password("aDmIn*/8558/*")
                .role(ADMIN)
                .gender(FEMALE)
                .build();

        Product product = Product.builder()
                .id(1002L)
                .name("iPhone 12")
                .company("Apple")
                .price(1200F)
                .quantity(5)
                .description("Some specs...!")
                .build();

        Wish wish = Wish.builder()
                .product(product)
                .user(user)
                .build();

        when(wishRepositoryMock.existsByUserAndProduct(user, product)).thenReturn(true);
        when(wishRepositoryMock.save(wish)).thenReturn(wish);

        boolean isProductAdded = userService.addProductToWishlist(user, product);

        assertFalse(isProductAdded);
    }

    @Test
    public void productIsRemovedFromWishlistIfExistsTest() {

        User user = User.builder()
                .id(1L)
                .firstName("Caroline")
                .lastName("Thomson")
                .username("admin")
                .password("aDmIn*/8558/*")
                .role(ADMIN)
                .gender(FEMALE)
                .build();

        Product product = Product.builder()
                .id(1002L)
                .name("iPhone 12")
                .company("Apple")
                .price(1200F)
                .quantity(5)
                .description("Some specs...!")
                .build();

        Wish wish = Wish.builder()
                .product(product)
                .user(user)
                .build();

        when(wishRepositoryMock.existsByUserAndProduct(user, product)).thenReturn(true);
        when(wishRepositoryMock.getByUserAndProduct(user, product)).thenReturn(wish);

        boolean isProductRemoved = userService.removeProductFromWishlist(user, product);

        assertTrue(isProductRemoved);
    }

    @Test
    public void productIsNotRemovedFromWishlistIfNotExistsTest() {

        User user = User.builder()
                .id(1L)
                .firstName("Caroline")
                .lastName("Thomson")
                .username("admin")
                .password("aDmIn*/8558/*")
                .role(ADMIN)
                .gender(FEMALE)
                .build();

        Product product = Product.builder()
                .id(1002L)
                .name("iPhone 12")
                .company("Apple")
                .price(1200F)
                .quantity(5)
                .description("Some specs...!")
                .build();

        Wish wish = Wish.builder()
                .product(product)
                .user(user)
                .build();

        when(wishRepositoryMock.existsByUserAndProduct(user, product)).thenReturn(false);
        when(wishRepositoryMock.getByUserAndProduct(user, product)).thenReturn(wish);

        boolean isProductRemoved = userService.removeProductFromWishlist(user, product);

        assertFalse(isProductRemoved);
    }

    @Test
    public void isProductInWishlistTest() {

        User user = User.builder()
                .id(1L)
                .firstName("Caroline")
                .lastName("Thomson")
                .username("admin")
                .password("aDmIn*/8558/*")
                .role(ADMIN)
                .gender(FEMALE)
                .build();

        Product product = Product.builder()
                .id(1002L)
                .name("iPhone 12")
                .company("Apple")
                .price(1200F)
                .quantity(5)
                .description("Some specs...!")
                .build();

        when(wishRepositoryMock.existsByUserAndProduct(user, product)).thenReturn(true);

        boolean isProductInWishlist= userService.isProductInWishlist(user, product);

        assertTrue(isProductInWishlist);
    }
}