package com.example.userservice.controller.UserControllerTest;

import com.example.userservice.model.User;
import com.example.userservice.repository.UserRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    private MockMvc mockMvc;

    private UserRepository userRepository;

    private static PostgreSQLContainer<?> postgreSQLContainer;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserControllerTest.class);

    @BeforeEach
    public void setUp(){
        if(postgreSQLContainer == null){
            postgreSQLContainer = new PostgreSQLContainer<>("postgres:15")
                    .withDatabaseName("testdb")
                    .withUsername("test")
                    .withPassword("test");
            postgreSQLContainer.start();
        }

        // Set the Spring datasource URL dynamically to use the test container's database
        System.setProperty("spring.datasource.url", postgreSQLContainer.getJdbcUrl());
        System.setProperty("spring.datasource.username", postgreSQLContainer.getUsername());
        System.setProperty("spring.datasource.password", postgreSQLContainer.getPassword());
    }

    @Test
    public void testCreateUser() throws  Exception{
        String userJson = "{ \"name\": \"John Doe\", \"email\": \"john.doe@example.com\" }";
        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.email").value("john.doe@example.com"));
    }

    @Test
    public void testGetUserById() throws Exception{
        User user = new User(10L,"Jane Doe", "jane.doe@example.com","555555555");
        LOGGER.info("ID = "+user.getId());
        userRepository.save(user);
        mockMvc.perform(get("/users/{id}", user.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Jane Doe"))
                .andExpect(jsonPath("$.email").value("jane.doe@example.com"));
    }

    @Test
    public void testGetAllUsers() throws Exception{
        User user1 = new User("John Doe", "john.doe@example.com", "3333333");
        User user2 = new User("Jane Doe", "jane.doe@example.com", "2222222");
        LOGGER.info("ID1 = "+user1.getId());
        LOGGER.info("ID2 = "+user2.getId());
        userRepository.save(user1);
        userRepository.save(user2);

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }
}
