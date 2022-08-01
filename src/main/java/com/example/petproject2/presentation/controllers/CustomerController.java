package com.example.petproject2.presentation.controllers;

import com.example.petproject2.domain.services.CustomerService;
import com.example.petproject2.presentation.DTO.CustomerDTO;
import com.example.petproject2.presentation.mapper.MainMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private final MainMapper mapper;

    @GetMapping()
    public List<CustomerDTO> findAll() {
        return mapper.toDTOs(customerService.findAllCustomers());
    }

    @PostMapping()
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO) {
        return mapper.toDTO(customerService.saveCustomer(mapper.toModel(customerDTO)));
    }

    @GetMapping("/{customerId}")
    public CustomerDTO findById(@PathVariable String customerId) {
        return mapper.toDTO(customerService.findById(customerId));
    }

    @PostMapping("/{customerId}/product/{productId}")
    public CustomerDTO saveProductToCustomer(@PathVariable String customerId, @PathVariable String productId) {
        return mapper.toDTO(customerService.saveProductToCustomer(customerId, productId));
    }

//    @DeleteMapping("/{customerId}")
//    public void deleteById(@PathVariable Long customerId) {
//        customerService.deleteById(customerId);
//    }
}
