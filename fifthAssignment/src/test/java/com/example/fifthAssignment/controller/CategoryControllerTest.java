package com.example.fifthAssignment.controller;

import com.example.fifthAssignment.FifthAssignmentApplication;
import com.example.fifthAssignment.model.Category;
import com.example.fifthAssignment.repository.CategoryRepository;
import com.example.fifthAssignment.service.category.CategoryService;
import com.example.fifthAssignment.utility.RunAfterStartup;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
    private CategoryRepository categoryRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @AfterEach
    public void afterEach() {
        categoryRepository.deleteAll();
    }

    @Test
    void getAllCategories() throws Exception {
        List<Category> categoriesBeforeSave = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            categoriesBeforeSave.add(categoryService.createCategory(new Category("Category" + i)));
        }
        MvcResult mvcResult = mockMvc.perform(get("/category").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        List<Category> categoriesAfterSave = parseListResponse(mvcResult);
        assertEquals(categoriesBeforeSave.size(), categoriesAfterSave.size());
        for (int i = 0; i < categoriesBeforeSave.size(); i++) {
            assertEquals(categoriesBeforeSave.get(i).getName(), categoriesAfterSave.get(i).getName());
        }
    }

    private List<Category> parseListResponse(MvcResult mvcResult) {
        try {
            return objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<>() {
            });
        } catch (JsonProcessingException | UnsupportedEncodingException e) {
            throw new RuntimeException("Error parsing json", e);
        }
    }
}