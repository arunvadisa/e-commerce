package com.arun.ecommerce.entity;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "customer")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank(message = "First Name is requried")
	@Size(min = 3, max = 30, message = "First Name should be between 3 and 20 charecters")
	private String firstname;
	
	@NotBlank(message = "Last Name is requried")
	@Size(min = 2, max = 20, message = "First Name should be between 2 and 20 charecters")
	private String lastname;
	
	@NotBlank(message = "Email is requried")
	@Email(message = "Invalid email formate")
	private String email;

//	@OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "address_id", referencedColumnName = "id")
	@Embedded
	private Address address;
}

/*
 * Join the address class data into customer
 * 
 */
