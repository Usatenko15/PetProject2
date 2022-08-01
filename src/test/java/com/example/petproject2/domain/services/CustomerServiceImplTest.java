package com.example.petproject2.domain.services;

import com.example.petproject2.domain.model.CustomerModel;
import com.example.petproject2.domain.model.ProductModel;
import com.example.petproject2.persistance.repository.PostgresRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    @Mock
    private PostgresRepository repository;
    @InjectMocks
    private CustomerServiceImpl customerService;

    @Test
    void findAllCustomers() {
        CustomerModel customer1 = new CustomerModel();
        customer1.setCustomerId("1l");
        customer1.setName("fdd");

        CustomerModel customer2 = new CustomerModel();
        customer2.setCustomerId("2l");
        customer2.setName("f1d4d");

        List<CustomerModel> customers = List.of(customer1,customer2);
        when(repository.findAllCustomers()).thenReturn(customers);
        assertEquals(customers, customerService.findAllCustomers());
    }

    @Test
    void saveCustomer() {
        CustomerModel customer = new CustomerModel();
        customer.setCustomerId("1l");
        customer.setName("fdd");

        when(repository.saveCustomer(customer)).thenReturn(customer);
        assertEquals(customer, customerService.saveCustomer(customer));
    }

    @Test
    void saveProductToCustomer() {
        CustomerModel customer = new CustomerModel();
        customer.setCustomerId("1l");
        customer.setName("fdd");

        ProductModel product = new ProductModel();
        product.setProductId("1l");
        product.setName("fdd");
        when(repository.saveProductToCustomer(customer.getCustomerId(), product.getProductId())).thenReturn(customer);
        assertEquals(customer,customerService.saveProductToCustomer(customer.getCustomerId(), product.getProductId()));
    }

    @Test
    void findById() {
        CustomerModel customer = new CustomerModel();
        customer.setCustomerId("1");
        customer.setName("fdd");

        when(repository.findById(customer.getCustomerId())).thenReturn(customer);
        assertEquals(customer,customerService.findById(customer.getCustomerId()));
    }
}