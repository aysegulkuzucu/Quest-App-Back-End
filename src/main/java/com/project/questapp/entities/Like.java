package com.project.questapp.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name="p_like")
@Data
public class Like {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	//Bircok postun bir user'i olabilir
	@ManyToOne(fetch = FetchType.LAZY)
	// like tablosundaki post_id kolonu ile user tablosunu bagladigimizi soyluyoruz
	@JoinColumn(name="post_id", nullable = false) //bu alan null olmasin
	// Bir user silindiginde onun tum poslari da silinsin
	@OnDelete(action = OnDeleteAction.CASCADE)
	// Serilization kisminda problem cikarmamasi icin bu alani igonore eder
	@JsonIgnore
	Post post;
	
	
	//Bircok postun bir user'i olabilir
	@ManyToOne(fetch = FetchType.LAZY)
	// like tablosundaki user_id kolonu ile user tablosunu bagladigimizi soyluyoruz
	@JoinColumn(name="user_id", nullable = false) //bu alan null olmasin
	// Bir user silindiginde onun tum poslari da silinsin
	@OnDelete(action = OnDeleteAction.CASCADE)
	// Serilization kisminda problem cikarmamasi icin bu alani igonore eder
	@JsonIgnore
	User user;


}
