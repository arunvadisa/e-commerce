package com.arun.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arun.ecommerce.entity.Customer;
import com.arun.ecommerce.exceptionHandler.CustomerNotAvilableException;
import com.arun.ecommerce.exceptionHandler.CustomerNotFoundException;
import com.arun.ecommerce.repository.CustomerRepository;

import jakarta.validation.Valid;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	public Customer saveCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	public Customer updateCustomer(Integer id, @Valid Customer customer) {
		return customerRepository.findById(id).map(existingCustomer -> {
			existingCustomer.setFirstname(customer.getFirstname());
			existingCustomer.setLastname(customer.getLastname());
			existingCustomer.setEmail(customer.getEmail());
			return customerRepository.save(existingCustomer);
		}).orElseThrow(() -> new CustomerNotAvilableException());
	}

	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	public Customer getCustomerById(Integer Id) {
		return customerRepository.findById(Id).orElseThrow(
				() -> new CustomerNotFoundException("Customer Not Found | Please provide register Customer Id"));
	}

	public void deleteCustomerById(Integer Id) {
		if (!customerRepository.existsById(Id)) {
			throw new CustomerNotAvilableException();
		}
		customerRepository.deleteById(Id);
	}

}
