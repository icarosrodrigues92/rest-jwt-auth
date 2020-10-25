package com.restjwtauth.repositories;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restjwtauth.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, BigInteger> {

	User findByEmail(String email);
}
