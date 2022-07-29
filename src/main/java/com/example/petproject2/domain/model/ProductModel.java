package com.example.petproject2.domain.model;

import com.example.petproject2.persistance.entity.MongoEntity.MongoProduct;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
public class ProductModel {
    private String productId;
    private String name;
    private List<CustomerModel> customers = new ArrayList<>();

    public ProductModel(MongoProduct product) {
        this.productId = product.getProductId();
        this.name = product.getName();
    }
}
