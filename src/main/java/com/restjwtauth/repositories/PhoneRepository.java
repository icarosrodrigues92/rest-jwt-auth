package com.restjwtauth.repositories;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restjwtauth.models.Phone;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, BigInteger> {

}
