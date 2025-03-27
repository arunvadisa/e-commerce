package com.arun.ecommerce.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Entity
//@Table(name = "address")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Embeddable
public class Address {
	
//	@Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
	private String street;
	private String housenumber;
	private String zipcode;
}
