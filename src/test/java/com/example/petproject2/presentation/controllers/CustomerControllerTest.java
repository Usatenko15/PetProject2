package com.example.petproject2.presentation.controllers;

import com.example.petproject2.domain.model.CustomerModel;
import com.example.petproject2.domain.services.CustomerService;
import com.example.petproject2.persistance.entity.PostgresEntity.Customer;
import com.example.petproject2.presentation.DTO.CustomerDTO;
import com.example.petproject2.presentation.mapper.MainMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {
    @MockBean
    private CustomerService customerService;
    @MockBean
    MainMapper mapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void findAll() {
    }

    @Test
    void saveCustomer() {
    }

    @Test
    void findById() throws Exception {
        CustomerModel customerModel = new CustomerModel();
        customerModel.setCustomerId("1");
        customerModel.setName("name");

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomerId(customerModel.getCustomerId());
        customerDTO.setName(customerModel.getName());

        when(customerService.findById("1")).thenReturn(customerModel);
        when(mapper.toDTO(customerModel)).thenReturn(customerDTO);

        mockMvc.perform(get("http://localhost:8080/api/v1/customer/{id}", 1))
                .andExpect(jsonPath("$.customerId").isNotEmpty())
                .andExpect(jsonPath("$.name").isNotEmpty());
    }

    @Test
    void saveProductToCustomer() {
    }
}