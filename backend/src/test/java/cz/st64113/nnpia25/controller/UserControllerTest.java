package cz.st64113.nnpia25.controller;

import cz.st64113.nnpia25.Nnpia25Application;
import cz.st64113.nnpia25.domain.User;
import cz.st64113.nnpia25.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = Nnpia25Application.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        User user = new User(69L, "nnpia25", "nnpia25@gmail.com");
        userRepository.save(user);
        userRepository.flush();
    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    void findAllUsers() throws Exception {
        mockMvc.perform(get("/api/v1/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void findAllUsers_givenEmail() throws Exception {
        mockMvc.perform(get("/api/v1/users?email=nnpia25@gmail.com"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(69)))
                .andExpect(jsonPath("$[0].password", is("nnpia25")))
                .andExpect(jsonPath("$[0].email", is("nnpia25@gmail.com")));
    }

    @Test
    void findAllUsers_givenWrongEmail_returnsEmptyList() throws Exception {
        mockMvc.perform(get("/api/v1/users?email=nnpia69@gmail.com"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    void findUser() throws Exception {
        mockMvc.perform(get("/api/v1/users/69"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(69)))
                .andExpect(jsonPath("$.password", is("nnpia25")))
                .andExpect(jsonPath("$.email", is("nnpia25@gmail.com")));
    }

    @Test
    void findUser_givenWrongId_returns404() throws Exception {
        mockMvc.perform(get("/api/v1/users/68"))
                .andExpect(status().isNotFound());
    }


}