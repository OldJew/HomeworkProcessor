package ru.oldjew.homeworkprocessor.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@SpringBootTest
@AutoConfigureMockMvc
class HomeworkControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getUser() throws Exception {
        mockMvc.perform(
                get("/api/getUser?id=2222"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("FirstName"));
    }

    @Test
    void getAllUsers() throws Exception {
        mockMvc.perform(
                get("/api/users/{id}", 0))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.value").value(1));
    }

    @Test
    void addUser() throws Exception {
        mockMvc.perform(
                post("/api/addUser?firstName=John&lastName=Doe"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.value").value(1));

    }

    @Test
    void addHomework() throws Exception {
        mockMvc.perform(
                        post("/api/addHomework?userId=2222&title=Test"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.value").value(1));
    }

    @Test
    void getHomework() throws Exception {
        mockMvc.perform(
                get("/api/getHomework?id=4444"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Test homework"));
    }

    @Test
    void getAllHomeworks() throws Exception {
        mockMvc.perform(
                        get("/api/homeworks/{id}", 0))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.value").value(1));
    }

    @Test
    void updateHomework() throws Exception {
        mockMvc.perform(
                put("/api/updateHomework?id=4444&status=true"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.done").value(true));
    }

    @Test
    void deleteHomework() throws Exception {
        mockMvc.perform(
                delete("/api/deleteHomework?id=1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.value").value(1));
    }
}