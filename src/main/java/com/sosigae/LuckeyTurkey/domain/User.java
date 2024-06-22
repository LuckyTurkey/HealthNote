package com.sosigae.LuckeyTurkey.domain;


import java.io.Serializable;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
@Table(name = "USERS")
public class User implements Serializable {
	private String id;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "patient_seq")
	@SequenceGenerator(name = "patient_seq", sequenceName = "patient_seq", allocationSize = 1)
	@Column(name = "user_id")
	private int userId; //int로 바꿈
	private String password;
	private String name;
	private String email;
	private String phone;
	private int is_admin;
	@Column(name = "personal_code")
	private String personalCode;

	//회원가입
	@Transient
    private int hospitalId; 
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int user_id) {
		this.userId = user_id;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public String getName() {
		return name;
	}
	public String getEmail() {
		return email;
	}
	public String getPersonalCode() {
		return personalCode;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPersonalCode(String personal_code) {
		this.personalCode = personal_code;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getIs_admin() {
		return is_admin;
	}
	public void setIs_admin(int is_admin) {
		this.is_admin = is_admin;
	}





}