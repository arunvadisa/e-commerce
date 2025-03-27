package com.arun.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arun.ecommerce.entity.Product;
import com.arun.ecommerce.exceptionHandler.ProductNotAvilableException;
import com.arun.ecommerce.exceptionHandler.ProductNotFoundException;
import com.arun.ecommerce.exceptionHandler.ProductServiceException;
import com.arun.ecommerce.model.ProductRequest;
import com.arun.ecommerce.model.ProductResponse;
import com.arun.ecommerce.repository.ProductRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public Long addProduct(ProductRequest productRequest) {
		Product product = Product.builder().productName(productRequest.getProductName())
				.price(productRequest.getPrice()).quantity(productRequest.getQuantity()).build();
		productRepository.save(product);
		return product.getProductId();
	}

	public List<Product> getAllCustomers() {
		List<Product> productList = productRepository.findAll();
		return productList;
	}

	public ProductResponse getProductById(Long productId) {
		Product product = productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException(
				"Product Id : " + productId + " is not Found | Please pass register Id"));
		ProductResponse productResponse = ProductResponse.builder().productName(product.getProductName())
				.price(product.getPrice()).quantity(product.getQuantity()).build();
		return productResponse;
	}

	public void deleteProductById(Long productId) {
		if (!productRepository.existsById(productId)) {
			throw new ProductNotAvilableException();
		}
		productRepository.deleteById(productId);

	}

	public Product updateProduct(Long id, Product product) {
		return productRepository.findById(id).map(existingProduct -> {
			if (product.getProductName() != null) {
				existingProduct.setProductName(product.getProductName());
			}
			if (product.getPrice() != 0) {
				existingProduct.setPrice(product.getPrice());
			}
			if (product.getQuantity() != 0) {
				existingProduct.setQuantity(product.getQuantity());
			}
			return productRepository.save(existingProduct);
		}).orElseThrow(() -> new ProductNotAvilableException());
	}

	public void reduceQuantity(Long productId, int quantity) {
		log.info("Reducing Quantity initiated");
		Product product = productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException(
				"Product Id : " + productId + " is not Found | Please pass register Id"));
		if(product instanceof Product) {
			log.info("Check Product Quantity");
			if(product.getQuantity() < quantity) {
				throw new ProductServiceException("not having enough quantity of products ","Not_ENOUGH_QUANTITY");
			}
			product.setQuantity(product.getQuantity() - quantity);
			productRepository.save(product);
			log.info("Product saved in DB");
		}
	}


}
