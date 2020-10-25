package com.restjwtauth.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.restjwtauth.models.User;
import com.restjwtauth.models.dto.UserDTO;
import com.restjwtauth.services.TokenService;
import com.restjwtauth.services.UserService;
import com.restjwtauth.utils.Mapper;

@RestController
public class RestJwtAuthController {

	@Autowired
	private UserService userService;

	@Autowired
	private TokenService tokenService;

	@RequestMapping(value = "/signin", method = RequestMethod.GET)
	public ResponseEntity<String> signin(@RequestBody UserDTO input) {

		return new ResponseEntity<String>(userService.authenticate(input.getEmail(), input.getPassword()),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public ResponseEntity<String> signup(@RequestBody UserDTO input) {

		User user = userService.save(input, null);
		return new ResponseEntity<String>(tokenService.generateToken(user), HttpStatus.OK);
	}

	@RequestMapping(value = "/me", method = RequestMethod.GET)
	public ResponseEntity<UserDTO> me(@RequestHeader("Authorization") String token) {

		User user = userService.find(null, null, token);
		return new ResponseEntity<UserDTO>(Mapper.mapperUserToUserDTO(user), HttpStatus.OK);
	}
}
