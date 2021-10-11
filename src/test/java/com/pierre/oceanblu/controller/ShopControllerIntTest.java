package com.pierre.oceanblu.controller;

import com.pierre.oceanblu.model.Product;
import com.pierre.oceanblu.model.Rating;
import com.pierre.oceanblu.model.Transaction;
import com.pierre.oceanblu.model.User;
import com.pierre.oceanblu.service.MainService;
import com.pierre.oceanblu.service.UserService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static com.pierre.oceanblu.model.Transaction.Code.IN;
import static com.pierre.oceanblu.model.Transaction.Code.OUT;
import static com.pierre.oceanblu.model.User.Gender.FEMALE;
import static com.pierre.oceanblu.model.User.Gender.MALE;
import static com.pierre.oceanblu.model.User.Role.ADMIN;
import static com.pierre.oceanblu.model.User.Role.USER;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class ShopControllerIntTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private MainService mainServiceMock;

    @MockBean
    private UserService userServiceMock;

    @InjectMocks
    private ShopController shopControllerMock;

    @Before
    public void before() {

        mvc = MockMvcBuilders.standaloneSetup(shopControllerMock)
                .apply(springSecurity())
                .build();
    }

    @Test
    void showShopTest() throws Exception {

        User authenticatedUser = User.builder()
                .id(2L)
                .firstName("Caroline")
                .lastName("Thomson")
                .username("admin")
                .password("aDmIn*/8558/*")
                .role(ADMIN)
                .gender(FEMALE)
                .build();

        when(userServiceMock.getAuthenticatedUser()).thenReturn(authenticatedUser);

        mvc.perform(get("/products").with(user("admin")))
                .andExpect(status().isOk())
                .andExpect(view().name("products"));
    }

    @Test
    void showProductTest() throws Exception {

        List<Transaction> transactions = Arrays.asList(
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
                        .user(User.builder()
                                .id(1L)
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
                        .user(User.builder()
                                .id(2L)
                                .firstName("Caroline")
                                .lastName("Thomson")
                                .username("admin")
                                .password("aDmIn*/8558/*")
                                .role(ADMIN)
                                .gender(FEMALE)
                                .build())
                        .currentItems(15)
                        .currentValue(15000F)
                        .build()
        );

        Long productId = 2L;

        String productName = "iPhone X";

        Product product = Product.builder()
                .id(productId)
                .name(productName)
                .company("Apple")
                .price(950F)
                .quantity(14)
                .description("Nice Phone!")
                .transactions(transactions)
                .build();

        User authenticatedUser = User.builder()
                .id(2L)
                .firstName("Caroline")
                .lastName("Thomson")
                .username("admin")
                .password("aDmIn*/8558/*")
                .role(ADMIN)
                .gender(FEMALE)
                .wishlist(new HashSet<>())
                .build();

        when(userServiceMock.getAuthenticatedUser()).thenReturn(authenticatedUser);

        when(mainServiceMock.getProductByID(productId)).thenReturn(product);

        mvc.perform(get("/products/{id}", productId).with(user("admin")))
                .andExpect(status().isOk())
                .andExpect(model().attribute("product",
                        hasProperty("name", equalTo(productName))))
                .andExpect(view().name("product"));
    }

    @Test
    void sellProductTest() throws Exception {

        mvc.perform(post("/products/{id}", 1L)
                        .with(user("admin"))
                        .queryParam("quantity", "5"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/products/1/thanks"));
    }

    @Test
    void thanksForPurchasingTest() throws Exception {

        List<Transaction> transactions = Arrays.asList(
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
                        .user(User.builder()
                                .id(1L)
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
                        .user(User.builder()
                                .id(2L)
                                .firstName("Caroline")
                                .lastName("Thomson")
                                .username("admin")
                                .password("aDmIn*/8558/*")
                                .role(ADMIN)
                                .gender(FEMALE)
                                .build())
                        .currentItems(15)
                        .currentValue(15000F)
                        .build()
        );

        Long productId = 2L;

        String productName = "iPhone X";

        Product product = Product.builder()
                .id(productId)
                .name(productName)
                .company("Apple")
                .price(950F)
                .quantity(14)
                .description("Nice Phone!")
                .transactions(transactions)
                .build();

        User authenticatedUser = User.builder()
                .id(2L)
                .firstName("Caroline")
                .lastName("Thomson")
                .username("admin")
                .password("aDmIn*/8558/*")
                .role(ADMIN)
                .gender(FEMALE)
                .build();

        when(userServiceMock.getAuthenticatedUser()).thenReturn(authenticatedUser);

        when(mainServiceMock.getProductByID(productId)).thenReturn(product);

        mvc.perform(get("/products/{id}/thanks", productId).with(user("admin")))
                .andExpect(status().isOk())
                .andExpect(view().name("thanks"));
    }

    @Test
    void isProductInWishlistTest() throws Exception {

        User user = User.builder()
                .id(2L)
                .firstName("Caroline")
                .lastName("Thomson")
                .username("admin")
                .password("aDmIn*/8558/*")
                .role(ADMIN)
                .gender(FEMALE)
                .build();

        List<Transaction> transactions = Arrays.asList(
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
                        .user(User.builder()
                                .id(1L)
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

        Long productId = 2L;

        String productName = "iPhone X";

        Product product = Product.builder()
                .id(productId)
                .name(productName)
                .company("Apple")
                .price(950F)
                .quantity(14)
                .description("Nice Phone!")
                .transactions(transactions)
                .build();

        when(userServiceMock.isProductInWishlist(user, product)).thenReturn(true);

        mvc.perform(get("/products/{id}/product-in-wishlist", productId).with(user("admin")))
                .andExpect(status().isOk());
    }

    @Test
    void addProductToWishlist() throws Exception {

        Long productId = 2L;

        mvc.perform(put("/products/{id}/add-to-wishlist", productId).with(user("admin")))
                .andExpect(status().isOk());
    }

    @Test
    void removeProductFromWishlist() throws Exception {

        Long productId = 2L;

        mvc.perform(delete("/products/{id}/remove-from-wishlist", productId).with(user("admin")))
                .andExpect(status().isOk());
    }
}