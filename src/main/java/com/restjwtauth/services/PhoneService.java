package com.restjwtauth.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restjwtauth.models.Phone;
import com.restjwtauth.models.User;
import com.restjwtauth.models.dto.PhoneDTO;
import com.restjwtauth.repositories.PhoneRepository;

@Service
public class PhoneService {

	@Autowired
	private PhoneRepository phoneRepository;

	public Phone savePhoneWithUser(PhoneDTO input, User user) {

		Phone phone = new Phone();
		phone.setArea_code(input.getArea_code());
		phone.setCountry_code(input.getCountry_code());
		phone.setNumber(input.getNumber());
		phone.setUser(user);

		return phoneRepository.save(phone);
	}
}
