package com.sosigae.LuckeyTurkey.domain;


import java.io.Serializable;

@SuppressWarnings("serial")
public class User implements Serializable {
	private String id;
	private String user_id; //String으로 바꿈
	private String password;
	private String name;
	private String email;
	private String phone;
	private String is_admin;
	private String personal_code;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUser_id() {
	        return user_id;
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
	public String getIs_admin() {
		return is_admin;
	}
	public String getPersonal_code() {
		return personal_code;
	}
	 public void setUser_id(String user_id) {
	        this.user_id = user_id;
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
	public void setIs_admin(String is_admin) {
		this.is_admin = is_admin;
	}
	public void setPersonal_code(String personal_code) {
		this.personal_code = personal_code;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
	

}
