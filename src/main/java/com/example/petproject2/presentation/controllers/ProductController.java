package com.example.petproject2.presentation.controllers;

import com.example.petproject2.domain.services.ProductService;
import com.example.petproject2.presentation.DTO.ProductDTO;
import com.example.petproject2.presentation.mapper.MainMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final MainMapper mapper;

    @GetMapping()
    public List<ProductDTO> findAll() {
        return mapper.toDTO(productService.findAllProducts());
    }

    @GetMapping("/{productId}")
    public ProductDTO findById(@PathVariable String productId) {
        return mapper.toDTO(productService.findProductById(productId));
    }

    @PostMapping()
    public ProductDTO saveCustomer(@RequestBody ProductDTO productDTO) {
        return mapper.toDTO(productService.saveProduct(mapper.toModel(productDTO)));
    }
}
