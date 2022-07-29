package com.example.petproject2.persistance.repository;

import com.example.petproject2.persistance.mappers.MongoMapper;
import com.example.petproject2.domain.model.CustomerModel;
import com.example.petproject2.domain.model.ProductModel;
import com.example.petproject2.persistance.entity.MongoEntity.MongoCustomer;
import com.example.petproject2.persistance.entity.MongoEntity.MongoProduct;
import com.example.petproject2.persistance.repository.mongorepository.CustomerMongoRepository;
import com.example.petproject2.persistance.repository.mongorepository.ProductMongoRepository;
import com.example.petproject2.presentation_layer.mapper.MainMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class MongoRepository implements MainRepository {
    @Autowired
    CustomerMongoRepository customerMongoRepository;
    @Autowired
    ProductMongoRepository productMongoRepository;
    @Autowired
    MainMapper mapper;
    @Autowired
    MongoMapper mongoMapper;


    @Transactional(readOnly = true)
    public List<CustomerModel> findAllCustomers() {
        return mongoMapper.toModels(customerMongoRepository.findAll());
    }

    @Transactional
    public CustomerModel saveCustomer(CustomerModel customerModel) {
        MongoCustomer customer = mongoMapper.toEntity(customerModel);

        return mongoMapper.toModel(customerMongoRepository.save(customer));
    }

    @Transactional
    public CustomerModel saveProductToCustomer(String customerId, String productId) {
        CustomerModel customer = mongoMapper.toModel(customerMongoRepository.findById(customerId).orElseThrow());
        ProductModel product = mongoMapper.toModel(productMongoRepository.findById(productId).orElseThrow());
        customer.getProducts().add(product);
        product.getCustomers().add(customer);
        customerMongoRepository.save(mongoMapper.toEntity(customer));
        return customer;
    }
    @Transactional(readOnly = true)
    public CustomerModel findById(String customerId) {
        MongoCustomer customerEntity = customerMongoRepository.findById(customerId).orElseThrow();
        CustomerModel customerModel = mongoMapper.toModel(customerEntity);
        List<ProductModel> productModels = customerModel.getProducts();
        customerModel.setProducts(productModels);
        return customerModel;
    }

    public List<ProductModel> findAllProducts(){
        return mongoMapper.toModel(productMongoRepository.findAll());
    }

    public ProductModel findProductById(String productId){
        return mongoMapper.toModel(
                productMongoRepository.findById(productId).orElseThrow());
    }

    @Transactional
    public ProductModel saveProduct(ProductModel productModel){
        MongoProduct product = mongoMapper.toEntity(productModel);

        return mongoMapper.toModel(productMongoRepository.save(product));
    }
}
