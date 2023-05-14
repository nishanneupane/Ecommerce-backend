package com.nishanneupane.ecommerce.model;

import java.sql.Date;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="tokens")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationToken {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="token")
	private String token;
	
	@Column(name="created_date")
	private java.util.Date createdDate;
	
	@OneToOne(targetEntity = User.class,fetch = FetchType.EAGER)
	@JoinColumn(nullable = false,name="user_id")
	private User user;
	
	public AuthenticationToken(User user) {
		this.user=user;
		this.createdDate=new java.util.Date();
		this.token=UUID.randomUUID().toString();
		
	}

}
