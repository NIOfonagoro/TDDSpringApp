package com.spartaglobal.productservice.web;

import com.spartaglobal.productservice.model.Product;
import com.spartaglobal.productservice.service.ProductService;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {
    @MockBean
    private ProductService service;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("GET /product/1 - Found")
    void testGetProductByIdFound() throws Exception {
        // Setup our mocked service
        Product mockProduct = new Product(1,"Product Name", 10, 1);
        doReturn(Optional.of(mockProduct)).when(service).findById(1);

        // Execute the GET request
        mockMvc.perform(get("/product/{id}", 1))
                // Validate the response code and content type
                .
    }
}
