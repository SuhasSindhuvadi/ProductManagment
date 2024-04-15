package in.ps.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ps.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

	
}
