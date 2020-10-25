package com.restjwtauth.services;

import java.math.BigInteger;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.server.NotAcceptableStatusException;

import com.restjwtauth.models.User;
import com.restjwtauth.models.dto.PhoneDTO;
import com.restjwtauth.models.dto.UserDTO;
import com.restjwtauth.repositories.UserRepository;
import com.restjwtauth.utils.Constants;
import com.restjwtauth.utils.Tools;

import groovy.lang.MissingFieldException;

/**
 * Gosto da ideia de recursividade. Costumo criar métodos como 'buscar' ou
 * 'salvar' que estejam adaptados para diferentes cenários ao invés de criar um
 * buscarPorX, buscarPorY ou um método distinto pra insert e update.
 * 
 * @author Ícaro
 *
 */
@Service
public class UserService {

	Pattern pattern = Pattern.compile(Constants.EMAIL_REGEX);

	@Autowired
	private TokenService tokenService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PhoneService phoneService;

	public User find(BigInteger id, String email, String token) {

		User user = null;

		if (token != null && !tokenService.isTokenExpired(token)) {
			email = tokenService.getAccessFromToken(token);
		}

		if (id != null) {
			user = userRepository.findById(id)
					.orElseThrow(() -> new RuntimeException("User not find for the provided id: " + id));
		} else if (email != null) {
			user = userRepository.findByEmail(email);
		}

		return user;
	}

	/**
	 * Password SH1
	 * 
	 * @param input
	 * @param id
	 * @return User
	 */
	public User save(UserDTO input, String id) {

		User user = new User();

		if (StringUtils.hasText(id)) {
			user = userRepository.findById(new BigInteger(id))
					.orElseThrow(() -> new RuntimeException("User not found - " + id));
		}

		this.checkFields(input);

		user.setPassword(Tools.encryptPassword(input.getPassword()));
		user.setFirstName(input.getFirstName());
		user.setLastName(input.getLastName());
		user.setEmail(input.getEmail());
		user = userRepository.save(user);

		if (!StringUtils.hasText(id) && input.getPhones() != null) {
			for (PhoneDTO phone : input.getPhones()) {
				phoneService.savePhoneWithUser(phone, user);
			}
		}

		return this.find(user.getIdUser(), null, null);
	}

	public String authenticate(String email, String password) {

		if (email == null || password == null) {
			throw new MissingFieldException("Missing Fields", null, UserDTO.class);
		}

		User user = this.find(null, email, null);
		if (user != null) {
			String sh1password = Tools.encryptPassword(password);
			if (!user.getPassword().contentEquals(sh1password)) {
				throw new NotAcceptableStatusException("Invalid e-mail or password");
			}
		} else {
			throw new NotAcceptableStatusException("Invalid e-mail or password");
		}

		return tokenService.generateToken(user);
	}

	private void checkFields(UserDTO input) {

		if (input.missingFieldsToString() != null) {
			throw new MissingFieldException("Missing Fields", input.missingFieldsToString(), UserDTO.class);
		}

		if (!pattern.matcher(input.getEmail()).matches()) {
			throw new NotAcceptableStatusException("Invalid fields");
		}

		if (userRepository.findByEmail(input.getEmail()) != null) {
			throw new IllegalArgumentException("Email already exists");
		}
	}
}
