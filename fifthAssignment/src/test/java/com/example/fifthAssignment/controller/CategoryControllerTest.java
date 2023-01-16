package com.example.fifthAssignment.controller;

import com.example.fifthAssignment.FifthAssignmentApplication;
import com.example.fifthAssignment.model.Category;
import com.example.fifthAssignment.model.Product;
import com.example.fifthAssignment.service.category.CategoryService;
import com.example.fifthAssignment.utility.RunAfterStartup;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = FifthAssignmentApplication.class)
@AutoConfigureMockMvc
class CategoryControllerTest {
    @MockBean
    private RunAfterStartup runAfterStartup;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllCategories() throws Exception {
        List<Category> categories = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            categories.add(categoryService.createCategory(new Category("Category" + i)));
        }
        MvcResult mvcResult = mockMvc.perform(get("/category").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
//                .andExpect(jsonPath("$[*]", hasSize(20)))
//                .andExpect(jsonPath("$[0].id", is(1)))
//                .andExpect(jsonPath("$[0].name", is("Category0")));


        Product product = parseResponse(mvcResult);
//        assertEquals(product.getId(), productForUpdate.getId());
//        assertEquals(product.getName(), productForUpdate.getName());
//        assertEquals(product.getCategory().getId(), categoryForUpdate.getId());
//        assertEquals(product.getCategory().getName(), categoryForUpdate.getName());

    }

    private List<Product> parseResponse(MvcResult mvcResult) {
        try {
            return objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<>() {
            });
        } catch (JsonProcessingException | UnsupportedEncodingException e) {
            throw new RuntimeException("Error parsing json", e);
        }
    }
}