package com.arun.ecommerce.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrderRequest {

	private Long productId;
	private int quantity;
	private Double amount;
	private PaymentMode paymentMode;
	
}
