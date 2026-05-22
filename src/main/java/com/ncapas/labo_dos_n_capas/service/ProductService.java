package com.ncapas.labo_dos_n_capas.service;

import com.ncapas.labo_dos_n_capas.domain.entity.Product;

import java.util.UUID;

public interface ProductService {
    void createProduct(Product product);
    Product getProduct(String name);
    void updateProduct(UUID id, Product product);
    Product deleteProduct(UUID id);

}