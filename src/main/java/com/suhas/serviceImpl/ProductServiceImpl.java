package com.suhas.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suhas.entity.Product;
import com.suhas.exception.ProductNotFoundException;
import com.suhas.repository.ProductRepository;
import com.suhas.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductRepository productRepo;
	
	@Override
	public Product saveProduct(Product product) {
		return productRepo.save(product);
	}

	@Override
	public List<Product> getAllProduct() {
		return productRepo.findAll();
	}

	@Override
	public Product getProductById(Integer id) {
		Optional<Product> optionalProduct = productRepo.findById(id);
		return optionalProduct.orElseThrow(
				() -> new ProductNotFoundException("Product not found with ID: " + id));
	}

	@Override
	public String deleteProduct(Integer id) {
		Optional<Product> optionalProduct = productRepo.findById(id);
		
		if(optionalProduct.isPresent()) {
			productRepo.delete(optionalProduct.get());
			return "Product Delete Successfully";
		} else {
			throw new ProductNotFoundException("Product not found with ID: " + id);
		}
	}
	
	@Override
	public Product editProduct(Product p, Integer id) {
		Optional<Product> optionalOldProduct = productRepo.findById(id);

		if(optionalOldProduct.isPresent()) {
			Product oldProduct = optionalOldProduct.get();
			oldProduct.setProductName(p.getProductName());
			oldProduct.setDescription(p.getDescription());
			oldProduct.setPrice(p.getPrice());
			oldProduct.setStatus(p.getStatus());
			oldProduct.setQuantity(p.getQuantity());
			return productRepo.save(oldProduct);
		} else {
			throw new ProductNotFoundException("Product not found with ID: " + id);
		}
	}

}
