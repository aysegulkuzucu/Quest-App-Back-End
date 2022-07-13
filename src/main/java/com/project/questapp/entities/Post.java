package com.project.questapp.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.Data;


@Entity
@Table(name="post")
@Data
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	//Bircok postun bir user'i olabilir
	@ManyToOne(fetch = FetchType.EAGER)
	// post tablosundaki user_id kolonu ile user tablosunu bagladigimizi soyluyoruz
	@JoinColumn(name="user_id", nullable = false) //bu alan null olmasin
	// Bir user silindiginde onun tum poslari da silinsin
	@OnDelete(action = OnDeleteAction.CASCADE)
	// Serilization kisminda problem cikarmamasi icin bu alani igonore eder
	User user;
	
	String title;
	
	
	@Lob
	@Column(columnDefinition="text")
	String text;
	
	@Temporal(TemporalType.TIMESTAMP)
	Date createDate;


	
	
	
}