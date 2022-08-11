package com.example.petproject2.integration;

import com.example.petproject2.domain.model.ProductModel;
import com.example.petproject2.domain.services.ProductService;
import com.example.petproject2.persistance.entity.PostgresEntity.Product;
import com.example.petproject2.persistance.repository.postgresrepository.CustomerProductRepository;
import com.example.petproject2.persistance.repository.postgresrepository.CustomerRepository;
import com.example.petproject2.persistance.repository.postgresrepository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@EnableAutoConfiguration(exclude = {
        DataSourceAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class})
public class ProductServiceIntegrationTest {
    @MockBean
    CustomerRepository customerRepository;
    @MockBean
    CustomerProductRepository customerProductRepository;
    @MockBean
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @Test
    void findProductById() {
        // given
        Product product = new Product();
        product.setProductId(1l);
        product.setName("name");

        given(productRepository.findById(1l)).willReturn(Optional.of(product));

        // when
        ProductModel productModel = productService.findProductById("1");

        // then
        assertEquals(product.getName(), productModel.getName());
    }

    @Test
    void findAllProducts() {
        // given
        Product product = new Product();
        product.setProductId(1l);
        product.setName("name");

        given(productRepository.findAll()).willReturn(List.of(product));

        // when
        ProductModel productModel = productService.findAllProducts().get(0);

        // then
        assertEquals(product.getName(), productModel.getName());
    }

    @Test
    void saveProduct() {
        // given
        ProductModel productModel = new ProductModel();
        productModel.setName("name");

        Product product = new Product();
        product.setProductId(1l);
        product.setName("name");

        given(productRepository.save(any())).willReturn(product);

        // when
        ProductModel expectedProductModel = productService.saveProduct(productModel);

        // then
        assertEquals(productModel.getName(), expectedProductModel.getName());
        assertEquals(product.getProductId().toString(), expectedProductModel.getProductId());
    }
}
