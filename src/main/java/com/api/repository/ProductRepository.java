package com.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.entity.Product;

public interface ProductRepository extends JpaRepository<Product,Integer>{

}
