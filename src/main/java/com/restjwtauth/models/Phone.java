package com.restjwtauth.models;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Phone {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "phone_sequence")
	@SequenceGenerator(name = "phone_sequence", sequenceName = "phone_seq")
	private BigInteger idPhone;

	@Column(name = "NUMBER", nullable = false)
	private long number;

	@Column(name = "AREA_CODE", nullable = false, unique = true)
	private long area_code;

	@Column(name = "COUNTRY_CODE", nullable = false)
	private String country_code;

	@ManyToOne
	@JoinColumn(name = "ID_USER")
	private User user;

	public BigInteger getIdPhone() {
		return idPhone;
	}

	public void setIdPhone(BigInteger idPhone) {
		this.idPhone = idPhone;
	}

	public long getNumber() {
		return number;
	}

	public void setNumber(long number) {
		this.number = number;
	}

	public long getArea_code() {
		return area_code;
	}

	public void setArea_code(long area_code) {
		this.area_code = area_code;
	}

	public String getCountry_code() {
		return country_code;
	}

	public void setCountry_code(String country_code) {
		this.country_code = country_code;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
