package com.ncapas.labo_dos_n_capas.repository;

import com.ncapas.labo_dos_n_capas.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    Product getProductByName(String name);
}