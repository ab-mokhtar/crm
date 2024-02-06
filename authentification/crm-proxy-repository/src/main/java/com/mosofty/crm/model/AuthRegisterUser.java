package com.mosofty.crm.model;


import java.io.Serializable;








public class AuthRegisterUser implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private int id;
	
	private String firstName;
	

	private String lastName;
	
	
	private String password;
	
	private String howcontact; 
	
	private String matchingPassword;
	
	
	
	private String email;
	
	
	private String tel;
	

	private String fax;
	
	
	private String fix;

	
	private String role;

	private int specialities;
	
	
	private String adresse;
	
	private String lng;
	
	private String lat;
	    
	private String wherejob;

	private String numeroPro;
	
	private int grade;
	
	private String Token;
	

	
	public AuthRegisterUser() {
		
	}




	public int getId() {
		return id;
	}




	public void setId(int id) {
		this.id = id;
	}




	public String getFirstName() {
		return firstName;
	}




	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}




	public String getLastName() {
		return lastName;
	}




	public void setLastName(String lastName) {
		this.lastName = lastName;
	}




	public String getPassword() {
		return password;
	}




	public void setPassword(String password) {
		this.password = password;
	}




	public String getHowcontact() {
		return howcontact;
	}




	public void setHowcontact(String howcontact) {
		this.howcontact = howcontact;
	}




	public String getMatchingPassword() {
		return matchingPassword;
	}




	public void setMatchingPassword(String matchingPassword) {
		this.matchingPassword = matchingPassword;
	}




	public String getEmail() {
		return email;
	}




	public void setEmail(String email) {
		this.email = email;
	}




	public String getTel() {
		return tel;
	}




	public void setTel(String tel) {
		this.tel = tel;
	}




	public String getFax() {
		return fax;
	}




	public void setFax(String fax) {
		this.fax = fax;
	}




	public String getFix() {
		return fix;
	}




	public void setFix(String fix) {
		this.fix = fix;
	}




	public String getRole() {
		return role;
	}




	public void setRole(String role) {
		this.role = role;
	}




	public int getSpecialities() {
		return specialities;
	}




	public void setSpecialities(int specialities) {
		this.specialities = specialities;
	}




	public String getAdresse() {
		return adresse;
	}




	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}




	public String getLng() {
		return lng;
	}




	public void setLng(String lng) {
		this.lng = lng;
	}




	public String getLat() {
		return lat;
	}




	public void setLat(String lat) {
		this.lat = lat;
	}




	public String getWherejob() {
		return wherejob;
	}




	public void setWherejob(String wherejob) {
		this.wherejob = wherejob;
	}




	public String getNumeroPro() {
		return numeroPro;
	}




	public void setNumeroPro(String numeroPro) {
		this.numeroPro = numeroPro;
	}




	public int getGrade() {
		return grade;
	}




	public void setGrade(int grade) {
		this.grade = grade;
	}
















	public String getToken() {
		return Token;
	}
















	public void setToken(String token) {
		Token = token;
	}




	
	

}
