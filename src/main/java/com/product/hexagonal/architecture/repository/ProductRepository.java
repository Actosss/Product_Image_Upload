package com.product.hexagonal.architecture.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.product.hexagonal.architecture.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

}
