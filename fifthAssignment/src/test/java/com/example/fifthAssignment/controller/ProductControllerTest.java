package com.example.fifthAssignment.controller;

import com.example.fifthAssignment.FifthAssignmentApplication;
import com.example.fifthAssignment.model.Category;
import com.example.fifthAssignment.model.Product;
import com.example.fifthAssignment.repository.CategoryRepository;
import com.example.fifthAssignment.service.product.ProductService;
import com.example.fifthAssignment.utility.RunAfterStartup;
import com.fasterxml.jackson.core.JsonProcessingException;
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

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = FifthAssignmentApplication.class)
@AutoConfigureMockMvc
class ProductControllerTest {
    @MockBean
    private RunAfterStartup runAfterStartup;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryRepository categoryRepository;

    @AfterEach
    public void afterEach() {
        categoryRepository.deleteAll();
    }

    @Test
    void findProductsByNameAndCategory() {
    }

    @Test
    void getProductById() throws Exception {
        Category category = categoryRepository.save(new Category("Cars"));
        Product product = productService.createProduct(new Product("Car", category));
        mockMvc.perform(get("/product/" + product.getId()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(product.getName())))
                .andExpect(jsonPath("$.category.name", is(category.getName())));
    }


    @Test
    void createProduct() throws Exception {
        String name = "Car";
        long categoryId = categoryRepository.save(new Category("Cars")).getId();
        String body = """
                {
                    "name": "%s",
                    "category": {
                                    "id": %d
                                }
                }
                """.formatted(name, categoryId);

        MvcResult mvcResult = mockMvc.perform(post("/product").contentType(MediaType.APPLICATION_JSON).content(body))
                .andExpect(status().isCreated()).andReturn();

        Long createdProductId = parseResponse(mvcResult, Product.class).getId();

        Product product = productService.findProductById(createdProductId);
        assertEquals(product.getId(), createdProductId);
        assertEquals(product.getName(), name);
        assertEquals(product.getCategory().getId(), categoryId);
    }


    @Test
    void updateProduct() throws Exception {
        Product oldProduct = productService.createProduct(new Product("Car", categoryRepository.save(new Category("Cars"))));

        Category categoryForUpdate = categoryRepository.save(new Category("Toys"));
        Product productForUpdate = productService.findProductById(oldProduct.getId());
        productForUpdate.setCategory(categoryForUpdate);
        productForUpdate.setName("Toy");

        MvcResult mvcResult = mockMvc.perform(put("/product").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productForUpdate)))
                .andExpect(status().isOk()).andReturn();

        Product product = parseResponse(mvcResult, Product.class);
        assertEquals(product.getId(), productForUpdate.getId());
        assertEquals(product.getName(), productForUpdate.getName());
        assertEquals(product.getCategory().getId(), categoryForUpdate.getId());
        assertEquals(product.getCategory().getName(), categoryForUpdate.getName());
    }


    @Test
    void deleteProduct() throws Exception {
        Category category = categoryRepository.save(new Category("Cars"));
        Product product = productService.createProduct(new Product("Car", category));
        mockMvc.perform(delete("/product/" + product.getId()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        assertThrows(NotFoundException.class,() -> productService.findProductById(product.getId()));
    }

    private <T> T parseResponse(MvcResult mvcResult, Class<T> c) {
        try {
            return objectMapper.readValue(mvcResult.getResponse().getContentAsString(), c);
        } catch (JsonProcessingException | UnsupportedEncodingException e) {
            throw new RuntimeException("Error parsing json", e);
        }
    }
}