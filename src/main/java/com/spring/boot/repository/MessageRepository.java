package com.spring.boot.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.spring.boot.model.MessageModel;
import com.spring.boot.security.AccountCredentials;

public interface MessageRepository extends MongoRepository<MessageModel, String> {
	List<MessageModel> findAllByOrderByCreateDateAsc();

	//AccountCredentials findByUserName(String username);
}
