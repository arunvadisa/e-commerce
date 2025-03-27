package com.arun.ecommerce.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arun.ecommerce.client.ProductService;
import com.arun.ecommerce.entity.Order;
import com.arun.ecommerce.model.OrderRequest;
import com.arun.ecommerce.repository.OrderRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private ProductService productService;

	public Long placeOrder(OrderRequest orderRequest) {
		log.info("Before placing order");
		productService.reduceQuantity(orderRequest.getProductId(), orderRequest.getQuantity());
		log.info("After checking product avilability for product "+orderRequest.getProductId());
		Order order = Order.builder().productId(orderRequest.getProductId()).price(orderRequest.getAmount())
				.orderDate(new Date()).orderStatus("CREATED").quantity(orderRequest.getQuantity()).build();
		log.info("Order placed");
		order = orderRepository.save(order);
		log.info("Product saved successfully");
		return order.getOrderId();
	}

}
