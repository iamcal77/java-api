package com.Java.MyJavaApi.Controller;

import com.Java.MyJavaApi.Models.Product;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private static final String JSON_FILE_PATH = "json/users.json";
    private final ObjectMapper objectMapper = new ObjectMapper();
    private List<Product> products = new ArrayList<>();
    private long idCounter = 1;

    public ProductController() {
        loadUsersFromFile();
        if (!products.isEmpty()) {
            idCounter = products.get(products.size() - 1).getUserId() + 1;
        }
    }
    @GetMapping
    public List<Product> getProducts() {
        return products;
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product) {

        product.setUserId(idCounter++);
        products.add(product);
        saveProductsToFile();
        return product;
    }

    private void saveProductsToFile() {
        try {
            objectMapper.writeValue(new File(JSON_FILE_PATH), products);
        } catch (IOException e) {
            throw new RuntimeException("Error saving users to file", e);
        }
    }


    private void loadUsersFromFile() {
        try {
            File file = new File(JSON_FILE_PATH);
            if (file.exists()) {
                products = objectMapper.readValue(file, new TypeReference<>() {
                });
            }
        } catch (IOException e) {
            throw new RuntimeException("Error loading users from file", e);
        }
    }
}
