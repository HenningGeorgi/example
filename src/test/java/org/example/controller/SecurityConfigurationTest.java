package org.example.controller;

import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class SecurityConfigurationTest {
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    @WithMockUser(roles = {"VIEWER"})
    public void create() throws Exception {
        mockMvc.perform(
                post("/greeting")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Henning\",\"age\":\"18\",\"vegan\":false}"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = {"VIEWER"})
    @Disabled
    public void createInvalid() throws Exception {
        mockMvc.perform(
                post("/greeting")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Henning\",\"age\":\"18\",\"vegan\":false}"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = {"VIEWER"})
    public void testdelete() throws Exception {
        MvcResult result = mockMvc.perform(
                post("/greeting").with(user("user").password("pass").roles("ADMIN"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Henning\",\"age\":\"18\",\"vegan\":false}"))
                .andExpect(status().isCreated())
                .andExpect(content().json("{\n" +
                        "    \"name\": \"Henning\",\n" +
                        "    \"age\": 18,\n" +
                        "    \"vegan\": false\n" +
                        "}"))
                .andReturn();

        String id = JsonPath.read(result.getResponse().getContentAsString(), "$.id");

        mockMvc.perform(
                delete("/greeting/{id}", id))
                .andExpect(status().isForbidden());
    }


    @Test
    @WithMockUser(roles = {"VIEWER"})
    public void deleteInvalid() throws Exception {
        mockMvc.perform(
                delete("/greeting/{id}", "0f81d478-6ece-4b59-8075-bd6a627d76fd"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = {"VIEWER"})
    public void testput() throws Exception {
        MvcResult result = mockMvc.perform(
                post("/greeting").with(user("user").password("pass").roles("ADMIN"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Henning\",\"age\":\"18\",\"vegan\":false}"))
                .andExpect(status().isCreated())
                .andExpect(content().json("{\n" +
                        "    \"name\": \"Henning\",\n" +
                        "    \"age\": 18,\n" +
                        "    \"vegan\": false\n" +
                        "}"))
                .andReturn();

        String id = JsonPath.read(result.getResponse().getContentAsString(), "$.id");

        mockMvc.perform(
                put("/greeting/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Example\",\"age\":\"20\",\"vegan\":true}"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = {"VIEWER"})
    public void putInvalid() throws Exception {
        mockMvc.perform(
                put("/greeting/{id}", "0f91d478-6ece-4b59-8075-bd6a627d76fd")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Example\",\"age\":\"20\",\"vegan\":true}"))
                .andExpect(status().isForbidden());
    }
}
