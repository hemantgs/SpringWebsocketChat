package com.spring.boot.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.spring.boot.security.AccountCredentials;

public interface UserRepository extends MongoRepository<AccountCredentials, String> {

	AccountCredentials findByUsername(String username);
}
