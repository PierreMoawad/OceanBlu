package com.pierre.oceanblu.controller;

import com.pierre.oceanblu.model.User;
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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Objects;

import static com.pierre.oceanblu.model.User.Gender.FEMALE;
import static com.pierre.oceanblu.model.User.Role.ADMIN;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class DefaultControllerIntTest {

    @MockBean
    private UserService userServiceMock;

    @InjectMocks
    private DefaultController defaultControllerMock;

    @Autowired
    private MockMvc mvc;

    @Before
    public void before() {

        mvc = MockMvcBuilders.standaloneSetup(defaultControllerMock)
                .apply(springSecurity())
                .build();
    }

    @Test
    public void getIndexWithoutUserTest() throws Exception {

        mvc.perform(get("/"))
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @Test
    public void getIndexWithUserTest() throws Exception {

        mvc.perform(get("/").with(user("user")))
                .andExpect(redirectedUrl("/products"));
    }

    @Test
    void showLoginFormAfterLogoutTest() throws Exception {

        MvcResult expectedResult = mvc.perform(get("/login?logout=true")).andReturn();

        assertTrue(expectedResult.getResponse().getContentAsString().contains("You have been logged out."));
        assertEquals(Objects.requireNonNull(expectedResult.getModelAndView()).getViewName(), "login");
    }

    @Test
    void showLoginFormAfterPasswordChange() throws Exception {

        MvcResult expectedResult = mvc.perform(get("/login?newPass=true")).andReturn();

        assertTrue(expectedResult.getResponse().getContentAsString().contains("Please login with new password"));
        assertEquals(Objects.requireNonNull(expectedResult.getModelAndView()).getViewName(), "login");
    }

    @Test
    void showRegistrationFormHasNewUserInModelTest() throws Exception {

        mvc.perform(get("/register"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attribute("user", instanceOf(User.class)))
                .andExpect(view().name("register"));
    }

    @Test
    void registerNewUserTest() throws Exception {

        User user = User.builder()
                .id(2L)
                .firstName("Caroline")
                .lastName("Thomson")
                .username("admin")
                .password("aDmIn*/8558/*")
                .role(ADMIN)
                .gender(FEMALE)
                .build();

        when(userServiceMock.isNewUser(user.getUsername())).thenReturn(true);

        mvc.perform(post("/register").flashAttr("user", user))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/login"));

        verify(userServiceMock, times(1)).registerUser(user);
    }

    @Test
    void registerExistingUserTest() throws Exception {

        User user = User.builder()
                .id(2L)
                .firstName("Caroline")
                .lastName("Thomson")
                .username("admin")
                .password("aDmIn*/8558/*")
                .role(ADMIN)
                .gender(FEMALE)
                .build();

        when(userServiceMock.isNewUser(user.getUsername())).thenReturn(false);

        mvc.perform(post("/register").flashAttr("user", user))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/register?error=true"));

        verify(userServiceMock, times(0)).registerUser(user);
    }
}