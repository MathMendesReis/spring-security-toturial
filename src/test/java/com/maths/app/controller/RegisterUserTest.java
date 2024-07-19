package com.maths.app.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maths.app.domain.auth.application.repositories.UserRepository;
import com.maths.app.domain.auth.enterprise.dto.RegisterDTO;
import com.maths.app.domain.auth.enterprise.model.User;
import com.maths.app.domain.auth.enterprise.model.UserRole;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class RegisterUserTest {
    private MockMvc mvc;
    @Autowired
    private WebApplicationContext context;

    @Autowired
    private UserRepository repository;

    @Before
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }

    @Test
    public void register_user_sucess() throws Exception{

        var result =mvc.perform(MockMvcRequestBuilders.post("/auth/register")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"login\":\"validLogin\",\"password\":\"validPassword\"\"role\":\"USER\"}"))
        .andExpect(MockMvcResultMatchers.status().isOk());
        System.out.println(result);

    }

}
