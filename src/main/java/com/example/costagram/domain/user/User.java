package com.example.costagram.domain.user;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;

import com.example.costagram.domain.image.Image;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; 
	
	@Column(length = 30, unique = true)
	private String username;
	
	@JsonIgnore
	private String password;
	
	private String name;
	private String website;
	private String bio;
	private String email;
	private String phone;
	private String gender;
	
	private String profileImage;
	private String provider; //제공자 Google, Facebook, Naver
	
	private String role; //USER, ADMIN
	

	//@JsonIgnoreProperties({"image"})
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<Image> images;
		
	private String profileImageUrl;
	
	@CreationTimestamp
	private Timestamp createDate;

}
