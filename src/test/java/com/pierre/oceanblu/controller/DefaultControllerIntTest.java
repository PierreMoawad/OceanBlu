package com.pierre.oceanblu.controller;

import com.pierre.oceanblu.config.WebConfig;
import com.pierre.oceanblu.service.UserService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ContextConfiguration(classes = WebConfig.class)
class DefaultControllerIntTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private UserService userService;

    @Before
    public void before() {

        mvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    public void getIndexWithoutUserTest() throws Exception {

        mvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(springSecurity())
                .build();

        String actualRedirect = "http://localhost/login";

        RequestBuilder requestBuilder = get("/");
        MvcResult expectedResult = mvc.perform(requestBuilder).andReturn();

        assertEquals(expectedResult.getResponse().getRedirectedUrl(), actualRedirect);
    }

    @Test
    public void getIndexWithUserTest() throws Exception {

        mvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(springSecurity())
                .build();

        String actualRedirect = "http://localhost/products";

        MvcResult expectedResult = mvc.perform(get("/").with(user("user"))).andReturn();

        assertEquals(expectedResult.getResponse().getRedirectedUrl(), actualRedirect);
    }

    @Test
    void showLoginForm() {
    }

    @Test
    void showRegistrationForm() {
    }

    @Test
    void registerUser() {
    }
}