package org.example.controller;

import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class GreetingControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void create() throws Exception {
        mockMvc.perform(
                post("/greeting")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Henning\",\"age\":\"18\",\"vegan\":false}"))
                .andExpect(status().isCreated())
                .andExpect(content().json("{\n" +
                        "    \"name\": \"Henning\",\n" +
                        "    \"age\": 18,\n" +
                        "    \"vegan\": false\n" +
                        "}"));
    }

    @Test
    public void createInvalid() throws Exception {
        mockMvc.perform(
                post("/greeting")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstname\":\"Henning\",\"age\":\"18\",\"vegan\":false}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testdelete() throws Exception {
        MvcResult result = mockMvc.perform(
                post("/greeting")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Henning\",\"age\":\"18\",\"vegan\":false}"))
                .andExpect(status().isCreated())
                .andExpect(content().json("{\n" +
                        "    \"name\": \"Henning\",\n" +
                        "    \"age\": 18,\n" +
                        "    \"vegan\": false\n" +
                        "}")).andReturn();

        String id = JsonPath.read(result.getResponse().getContentAsString(), "$.id");

        mockMvc.perform(
                delete("/greeting/{id}", id))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteInvalid() throws Exception {
        mockMvc.perform(
                delete("/greeting/{id}", "0f81d478-6ece-4b59-8075-bd6a627d76fd"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testput() throws Exception {
        MvcResult result = mockMvc.perform(
                post("/greeting")
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
                .andExpect(status().isOk())
                .andExpect(content().json("{\n" +
                        "    \"name\": \"Example\",\n" +
                        "    \"age\": 20,\n" +
                        "    \"vegan\": true\n" +
                        "}"));
    }

    @Test
    public void putInvalid() throws Exception {
        mockMvc.perform(
                put("/greeting/{id}", "0f81d478-6ece-4b59-8075-bd6a627d76fd")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Example\",\"age\":\"20\",\"vegan\":true}"))
                .andExpect(status().isNotFound());
    }

    /*@Test
    public void patch() throws Exception {
        mockMvc.perform(
                get("/greeting")
                        .contentType(MediaType.APPLICATION_JSON).content("/28d34abb-5c2e-4e3f-a4bb-3133f795674b"))
                .andExpect(status().isOk());
    }

    @Test
    public void patchInvalid() throws Exception {
        mockMvc.perform(
                get("/greeting")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("/uuid2834abb-5c2e-4e3f-a4bb-3133f795674b"))
                .andExpect(status().isNotFound());
    }*/
}
