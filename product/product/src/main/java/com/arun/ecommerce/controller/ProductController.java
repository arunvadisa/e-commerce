package com.arun.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.arun.ecommerce.entity.Product;
import com.arun.ecommerce.model.ProductRequest;
import com.arun.ecommerce.model.ProductResponse;
import com.arun.ecommerce.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService; 
	
	@PostMapping("/addProduct")
	public ResponseEntity<Long> addProduct(@RequestBody ProductRequest productRequest) {
		Long productId = productService.addProduct(productRequest);
		return new ResponseEntity<>(productId, HttpStatus.CREATED);
	}

	@PutMapping("/updateProductById/{Id}")
	public ResponseEntity<Product> updateProduct(@PathVariable Long Id,@RequestBody Product product) {
		return ResponseEntity.ok(productService.updateProduct(Id, product));
	}

	@GetMapping("/getAllProduct")
	public ResponseEntity<List<Product>> getAllProduct() {
		List<Product> productDetails = productService.getAllCustomers();
		return new ResponseEntity<List<Product>>(productDetails, HttpStatus.OK);
	}

	@GetMapping("/getProductById")
	public ResponseEntity<ProductResponse> getProductById(@RequestParam("id") Long productId) {
		ProductResponse response = productService.getProductById(productId);
		return new ResponseEntity<ProductResponse>(response, HttpStatus.OK);
	}

	@DeleteMapping("/deleteProduct/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable("id")  Long productId) {
		productService.deleteProductById(productId);
		return new ResponseEntity<String>("Product Id - " + productId + " is deleted successfully ", HttpStatus.OK);
	}
	@PutMapping("/reduceQuantity/{id}")
	public ResponseEntity<Void> reduceQuantity(@PathVariable ("id") Long productId, @RequestParam("quantity") int quantity ){
		productService.reduceQuantity(productId,quantity);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}
