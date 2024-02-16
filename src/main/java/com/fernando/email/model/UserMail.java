package com.fernando.email.model;

import jakarta.persistence.*;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name="userMail")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserMail {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "username",nullable = false)
	private String username;
	
	@Column(name="group_id",nullable=false)
	private Long groupId;
	
	@Column(name="email",nullable=false,unique=true)
	private String email;
}
