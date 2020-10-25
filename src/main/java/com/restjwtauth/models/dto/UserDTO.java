package com.restjwtauth.models.dto;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class UserDTO {

	private BigInteger idUser;

	private String firstName;

	private String lastName;

	private String email;

	private String password;

	private List<PhoneDTO> phones = new ArrayList<PhoneDTO>();

	public BigInteger getIdUser() {
		return idUser;
	}

	public void setIdUser(BigInteger idUser) {
		this.idUser = idUser;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<PhoneDTO> getPhones() {
		return phones;
	}

	public void setPhones(List<PhoneDTO> phones) {
		this.phones = phones;
	}
	
	public String missingFieldsToString() {
		
		List<String> missingFields = new ArrayList<>();
		if (this.getFirstName() == null) {
			missingFields.add("FirstName ");
		}
		if (this.getLastName() == null) {
			missingFields.add("LasttName ");
		}
		if (this.getEmail() == null) {
			missingFields.add("Email ");
		}
		if (this.getPassword() == null) {
			missingFields.add("Password ");
		}

		StringBuilder sb = null;
		if (!missingFields.isEmpty()) {
			sb = new StringBuilder();
			sb.append("UserDTO [ ");
			for (String field : missingFields) {
				sb.append(field);
			}
			sb.append("]");
		}
		
		return sb != null ? sb.toString() : null;
	}
}
