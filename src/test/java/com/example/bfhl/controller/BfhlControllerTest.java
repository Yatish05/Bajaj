package com.example.bfhl.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class BfhlControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetRoute() throws Exception {
        mockMvc.perform(get("/bfhl"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.operation_code").value(1));
    }

    @Test
    public void testHealthRoute() throws Exception {
        mockMvc.perform(get("/health"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("UP"));
    }

    @Test
    public void testPostRouteSuccess() throws Exception {
        String requestJson = "{\"data\": [\"a\", \"1\", \"334\", \"4\", \"R\", \"$\"]}";
        mockMvc.perform(post("/bfhl")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.is_success").value(true))
                .andExpect(jsonPath("$.user_id").value("yatish_bansal_06032005"))
                .andExpect(jsonPath("$.email").value("yatishbansal05@gmail.com"))
                .andExpect(jsonPath("$.roll_number").value("2310991439"))
                .andExpect(jsonPath("$.odd_numbers[0]").value("1"))
                .andExpect(jsonPath("$.even_numbers[0]").value("334"))
                .andExpect(jsonPath("$.even_numbers[1]").value("4"))
                .andExpect(jsonPath("$.alphabets[0]").value("A"))
                .andExpect(jsonPath("$.alphabets[1]").value("R"))
                .andExpect(jsonPath("$.special_characters[0]").value("$"))
                .andExpect(jsonPath("$.sum").value("339"))
                .andExpect(jsonPath("$.concat_string").value("Ra"));
    }

    @Test
    public void testPostRouteMalformedJson() throws Exception {
        String badRequestJson = "{invalid_json}";
        mockMvc.perform(post("/bfhl")
                .contentType(MediaType.APPLICATION_JSON)
                .content(badRequestJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.is_success").value(false))
                .andExpect(jsonPath("$.error").value("Malformed JSON request body"));
    }
}
