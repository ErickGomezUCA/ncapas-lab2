package com.ncapas.labo_dos_n_capas.service.impl;

import com.ncapas.labo_dos_n_capas.domain.entity.Product;
import com.ncapas.labo_dos_n_capas.repository.ProductRepository;
import com.ncapas.labo_dos_n_capas.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public void createProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public Product getProduct(String name) {
        return productRepository.getProductByName(name);
    }

    @Override
    public void updateProduct(UUID id, Product product) {
        Product existProduct = productRepository.getReferenceById(id);
        existProduct.setName(product.getName());
        existProduct.setPrice(product.getPrice());
        existProduct.setAvailable(product.getAvailable());
        productRepository.save(existProduct);
    }

    @Override
    public Product deleteProduct(UUID id) {
        Product existProduct = productRepository.getReferenceById(id);
        productRepository.deleteById(id);
        return existProduct;
    }
}
