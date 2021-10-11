package com.pierre.oceanblu.controller;

import com.pierre.oceanblu.model.*;
import com.pierre.oceanblu.service.UserService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.*;

import static com.pierre.oceanblu.model.Transaction.Code.IN;
import static com.pierre.oceanblu.model.Transaction.Code.OUT;
import static com.pierre.oceanblu.model.User.Gender.MALE;
import static com.pierre.oceanblu.model.User.Role.ADMIN;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerIntTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userServiceMock;

    @InjectMocks
    private UserController userControllerMock;

    @Before
    public void before() {

        mvc = MockMvcBuilders.standaloneSetup(userControllerMock)
                .apply(springSecurity())
                .build();
    }

    @Test
    void showUserProfileTest() throws Exception {

        Long userId = 1L;

        User user = User.builder()
                .id(userId)
                .firstName("Pierre")
                .lastName("Moawad")
                .username("admin")
                .password("admin")
                .role(ADMIN)
                .gender(MALE)
                .wishlist(new HashSet<>())
                .build();

        List<Transaction> purchases = Arrays.asList(
                Transaction.builder()
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
                        .user(user)
                        .rating(Rating.builder()
                                .id(1L)
                                .rate(4)
                                .reviewLeft(false)
                                .build())
                        .currentItems(12)
                        .currentValue(11400F)
                        .build(),

                Transaction.builder()
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
                        .user(user)
                        .currentItems(15)
                        .currentValue(15000F)
                        .build()
        );

        when(userServiceMock.getUserByID(userId)).thenReturn(user);

        when(userServiceMock.listAllPurchases(userId)).thenReturn(purchases);

        mvc.perform(get("/users/{id}", userId).with(user("admin").roles("ADMIN")))
                .andExpect(status().isOk())
                .andExpect(model().attribute("purchases", hasSize(purchases.size())))
                .andExpect(model().attribute("user", is(user)))
                .andExpect(view().name("user-profile"));
    }

    @Test
    void showUserWishlistTest() throws Exception {

        Long userId = 1L;

        User user = User.builder()
                .id(userId)
                .firstName("Pierre")
                .lastName("Moawad")
                .username("admin")
                .password("admin")
                .role(ADMIN)
                .gender(MALE)
                .build();

        Set<Wish> wishlist = new HashSet<>(Arrays.asList(
                Wish.builder()
                        .user(user)
                        .product(Product.builder()
                                .id(1000L)
                                .name("iPhone X")
                                .company("Apple")
                                .price(950F)
                                .quantity(14)
                                .description("Nice Phone!")
                                .transactions(new ArrayList<>())
                                .build())
                        .build(),

                Wish.builder()
                        .user(user)
                        .product(Product.builder()
                                .id(1001L)
                                .name("Galaxy S9")
                                .company("Samsung")
                                .price(1000F)
                                .quantity(3)
                                .description("Some specs...")
                                .transactions(new ArrayList<>())
                                .build())
                        .build()
        ));

        user.setWishlist(wishlist);

        when(userServiceMock.getUserByID(userId)).thenReturn(user);

        mvc.perform(get("/users/{id}/wishlist", userId).with(user("admin").roles("ADMIN")))
                .andExpect(status().isOk())
                .andExpect(model().attribute("products", hasSize(user.getProductsInWishlist().size())))
                .andExpect(model().attribute("user", is(user)))
                .andExpect(view().name("wishlist"));
    }
}