package com.suhas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.suhas.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

	
}
