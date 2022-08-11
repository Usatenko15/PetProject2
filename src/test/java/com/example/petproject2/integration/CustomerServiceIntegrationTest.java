package com.example.petproject2.integration;

import com.example.petproject2.domain.model.CustomerModel;
import com.example.petproject2.domain.services.CustomerService;
import com.example.petproject2.persistance.entity.PostgresEntity.Customer;
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
import static org.mockito.Mockito.when;

@SpringBootTest
@EnableAutoConfiguration(exclude = {
        DataSourceAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class})
public class CustomerServiceIntegrationTest {

    @MockBean
    CustomerRepository customerRepository;
    @MockBean
    CustomerProductRepository customerProductRepository;
    @MockBean
    private ProductRepository productRepository;

    @Autowired
    private CustomerService customerService;

    @Test
    void findById() {
        // given
        Customer customer = new Customer();
        customer.setCustomerId(1l);
        customer.setName("name");
        given(customerRepository.findById(1l)).willReturn(Optional.of(customer));

        // when
        CustomerModel customerModel = customerService.findById("1");

        // then
        assertEquals(customer.getName(), customerModel.getName());
    }

    @Test
    void findAllCustomers() {
        // given
        Customer customer = new Customer();
        customer.setCustomerId(1l);
        customer.setName("name");
        given(customerRepository.findAll()).willReturn(List.of(customer));

        // when
        CustomerModel customerModel = customerService.findAllCustomers().get(0);

        // then
        assertEquals(customer.getName(), customerModel.getName());
    }

    @Test
    void saveCustomer() {
        // given
        CustomerModel customerModel = new CustomerModel();
        customerModel.setName("name");

        Customer customer = new Customer();
        customer.setCustomerId(1l);
        customer.setName("name");

        given(customerRepository.save(any())).willReturn(customer);

        // when
        CustomerModel expectedCustomerModel = customerService.saveCustomer(customerModel);

        // then
        assertEquals(customerModel.getName(), expectedCustomerModel.getName());
        assertEquals(customer.getCustomerId().toString(), expectedCustomerModel.getCustomerId());
    }

    @Test
    void saveProductToCustomer() {
        //given
        Customer customer = new Customer();
        customer.setCustomerId(1l);
        customer.setName("fdd");

        Product product = new Product();
        product.setProductId(1l);
        product.setName("fdd");

        //when
        when(customerRepository.findById(any())).thenReturn(Optional.of(customer));
        when(productRepository.findById(any())).thenReturn(Optional.of(product));

        CustomerModel expectedCustomerModel = customerService.saveProductToCustomer("1", "1");

        //then
        assertEquals(customer.getName(), expectedCustomerModel.getName());
        assertEquals(customer.getCustomerId().toString(), expectedCustomerModel.getCustomerId());
    }
}
