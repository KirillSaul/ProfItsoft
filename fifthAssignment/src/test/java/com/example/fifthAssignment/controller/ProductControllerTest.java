package com.example.fifthAssignment.controller;

import com.example.fifthAssignment.FifthAssignmentApplication;
import com.example.fifthAssignment.exception.NotFoundException;
import com.example.fifthAssignment.model.Category;
import com.example.fifthAssignment.model.Product;
import com.example.fifthAssignment.protocol.product.ProductCreateDto;
import com.example.fifthAssignment.protocol.product.ProductUpdateDto;
import com.example.fifthAssignment.protocol.product.ProductViewDto;
import com.example.fifthAssignment.repository.CategoryRepository;
import com.example.fifthAssignment.service.category.CategoryService;
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
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
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
    @Autowired
    private CategoryService categoryService;

    @AfterEach
    public void afterEach() {
        categoryRepository.deleteAll();
    }

    @Test
    void productFilter() throws Exception {
        List<Category> categories = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            categories.add(categoryService.createCategory(new Category("Category" + i)));
            productService.createProduct(new ProductCreateDto("Product" + i, categories.get(i).getId()));
        }

        int pageSize = 10;
        int page = 0;
        String bodyPageSizeAndPageNumber = """
                {
                    "pageSize": %d,
                    "page": %d
                }
                """.formatted(pageSize, page);

        mockMvc.perform(post("/product/_filter").contentType(MediaType.APPLICATION_JSON).content(bodyPageSizeAndPageNumber))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(10)))
                .andExpect(jsonPath("$.pageable.pageNumber", is(0)))
                .andExpect(jsonPath("$.totalPages", is(2)));


        productService.createProduct(new ProductCreateDto("car", categories.get(0).getId()));
        productService.createProduct(new ProductCreateDto("care", categories.get(1).getId()));
        String productName = "car";
        List<Long> categoryIds = List.of(categories.get(0).getId(), categories.get(1).getId());
        String fullFilter = """
                {
                    "productName": "%s",
                    "categoryIds": %s,
                    "pageSize": %d,
                    "page": %d
                }
                """.formatted(productName, categoryIds, pageSize, page);
        mockMvc.perform(post("/product/_filter").contentType(MediaType.APPLICATION_JSON).content(fullFilter))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].name", is("car")))
                .andExpect(jsonPath("$.content[1].name", is("care")));
    }

    @Test
    void getProductById() throws Exception {
        Category category = categoryRepository.save(new Category("Cars"));
        ProductViewDto createdProductViewDto = productService.createProduct(new ProductCreateDto("Car", category.getId()));
       MvcResult mvcResult =  mockMvc.perform(get("/product/" + createdProductViewDto.getId()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        ProductViewDto productViewDto = parseResponse(mvcResult, ProductViewDto.class);

        productService.findProductViewDtoById(productViewDto.getId());
        assertEquals(productViewDto.getId(), createdProductViewDto.getId());
        assertEquals(productViewDto.getName(), createdProductViewDto.getName());
        assertEquals(productViewDto.getCategoryId(), createdProductViewDto.getCategoryId());
    }


    @Test
    void createProduct() throws Exception {
        String name = "Car";
        long categoryId = categoryRepository.save(new Category("Cars")).getId();
        String body = """
                {
                    "name": "%s",
                    "categoryId": %d
                }
                """.formatted(name, categoryId);

        MvcResult mvcResult = mockMvc.perform(post("/product").contentType(MediaType.APPLICATION_JSON).content(body))
                .andExpect(status().isCreated()).andReturn();

        Long createdProductId = parseResponse(mvcResult, ProductViewDto.class).getId();

        ProductViewDto productViewDto = productService.findProductViewDtoById(createdProductId);
        assertEquals(productViewDto.getId(), createdProductId);
        assertEquals(productViewDto.getName(), name);
        assertEquals(productViewDto.getCategoryId(), categoryId);
    }


    @Test
    void updateProduct() throws Exception {
        ProductViewDto oldProduct = productService.createProduct(new ProductCreateDto("Car", categoryRepository.save(new Category("Cars")).getId()));

        Category categoryForUpdate = categoryRepository.save(new Category("Toys"));
        ProductViewDto productViewDtoForUpdate = productService.findProductViewDtoById(oldProduct.getId());
        productViewDtoForUpdate.setCategoryId(categoryForUpdate.getId());
        productViewDtoForUpdate.setName("Toy");
        MvcResult mvcResult = mockMvc.perform(put("/product").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productViewDtoForUpdate)))
                .andExpect(status().isOk()).andReturn();

        ProductUpdateDto productUpdateDto = parseResponse(mvcResult, ProductUpdateDto.class);
        assertEquals(productUpdateDto.getId(), productViewDtoForUpdate.getId());
        assertEquals(productUpdateDto.getName(), productViewDtoForUpdate.getName());
        assertEquals(productUpdateDto.getCategoryId(), categoryForUpdate.getId());
    }


    @Test
    void deleteProduct() throws Exception {
        Category category = categoryRepository.save(new Category("Cars"));
        ProductViewDto productViewDto = productService.createProduct(new ProductCreateDto("Car", category.getId()));
        mockMvc.perform(delete("/product/" + productViewDto.getId()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        assertThrows(NotFoundException.class, () -> productService.findProductViewDtoById(productViewDto.getId()));
    }

    private <T> T parseResponse(MvcResult mvcResult, Class<T> c) {
        try {
            return objectMapper.readValue(mvcResult.getResponse().getContentAsString(), c);
        } catch (JsonProcessingException | UnsupportedEncodingException e) {
            throw new RuntimeException("Error parsing json", e);
        }
    }
}