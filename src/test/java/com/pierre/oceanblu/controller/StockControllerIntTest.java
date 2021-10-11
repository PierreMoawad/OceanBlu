package com.pierre.oceanblu.controller;

import com.pierre.oceanblu.model.Product;
import com.pierre.oceanblu.model.Rating;
import com.pierre.oceanblu.model.Transaction;
import com.pierre.oceanblu.model.User;
import com.pierre.oceanblu.model.form.PurchaseProductsForm;
import com.pierre.oceanblu.service.MainService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class StockControllerIntTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private MainService mainServiceMock;

    @MockBean
    private UserService userServiceMock;

    @InjectMocks
    private StockController stockControllerMock;

    @Before
    public void before() {

        mvc = MockMvcBuilders.standaloneSetup(stockControllerMock)
                .apply(springSecurity())
                .build();
    }

    @Test
    void showStockTest() throws Exception {

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

        User authenticatedUser = User.builder()
                .id(1L)
                .firstName("Pierre")
                .lastName("Moawad")
                .username("admin")
                .password("admin")
                .role(ADMIN)
                .gender(MALE)
                .wishlist(new HashSet<>())
                .build();

        when(userServiceMock.getAuthenticatedUser()).thenReturn(authenticatedUser);

        when(mainServiceMock.listAllTransactions()).thenReturn(transactions);

        mvc.perform(get("/stock").with(user("admin").roles("ADMIN")))
                .andExpect(status().isOk())
                .andExpect(model().attribute("transactions", hasSize(transactions.size())))
                .andExpect(view().name("stock"));
    }

    @Test
    void showPurchaseFormTest() throws Exception {

        List<Product> products = Arrays.asList(
                Product.builder()
                        .id(1000L)
                        .name("iPhone X")
                        .company("Apple")
                        .price(950F)
                        .quantity(14)
                        .description("Nice Phone!")
                        .build(),

                Product.builder()
                        .id(1001L)
                        .name("Galaxy S9")
                        .company("Samsung")
                        .price(1000F)
                        .quantity(3)
                        .description("Some specs...")
                        .build()
        );

        User authenticatedUser = User.builder()
                .id(1L)
                .firstName("Pierre")
                .lastName("Moawad")
                .username("admin")
                .password("admin")
                .role(ADMIN)
                .gender(MALE)
                .wishlist(new HashSet<>())
                .build();

        when(userServiceMock.getAuthenticatedUser()).thenReturn(authenticatedUser);

        when(mainServiceMock.listAllProducts()).thenReturn(products);

        mvc.perform(get("/stock/purchase-products").with(user("admin").roles("ADMIN")))
                .andExpect(status().isOk())
                .andExpect(model().attribute("products", hasSize(products.size())))
                .andExpect(model().attribute("form", instanceOf(PurchaseProductsForm.class)))
                .andExpect(view().name("purchase"));
    }

    @Test
    void savePurchasedProductsTest() throws Exception {

        mvc.perform(post("/stock/purchase-products").with(user("admin").roles("ADMIN")))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/stock"));
    }
}