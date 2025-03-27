package com.arun.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.arun.ecommerce.entity.Customer;
import com.arun.ecommerce.service.CustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/customer")
@Validated
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@PostMapping("/saveCustomer")
	public ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer customer) {
		Customer customerDetails = customerService.saveCustomer(customer);
		return new ResponseEntity<Customer>(customerDetails, HttpStatus.CREATED);
	}

	@PutMapping("/updateCustomerById/{Id}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable Integer Id, @Valid @RequestBody Customer customer) {
		return ResponseEntity.ok(customerService.updateCustomer(Id, customer));
	}

	@GetMapping("/getAllCustomer")
	public ResponseEntity<List<Customer>> fetchAllCustomers() {
		List<Customer> customerDetails = customerService.getAllCustomers();
		return new ResponseEntity<List<Customer>>(customerDetails, HttpStatus.OK);
	}

	@GetMapping("/getById")
	public ResponseEntity<Customer> fetchCustomerById(@RequestParam Integer Id) {
		Customer customerDetails = customerService.getCustomerById(Id);
		return new ResponseEntity<Customer>(customerDetails, HttpStatus.OK);
	}

	@DeleteMapping("/deleteCustomer/{Id}")
	public ResponseEntity<String> deleteCustomer(@PathVariable Integer Id) {
		customerService.deleteCustomerById(Id);
		return new ResponseEntity<String>("Customer Id - " + Id + " is deleted successfully ", HttpStatus.OK);
	}

}
