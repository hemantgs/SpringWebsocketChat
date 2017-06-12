package com.spring.boot.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.boot.model.MessageModel;
import com.spring.boot.repository.MessageRepository;

@Controller

public class ChatController {
	@Autowired
	private MessageRepository chatMessageRepository;

	// @RequestMapping(value = "/messages", method = RequestMethod.POST)
	@MessageMapping("/newMessage")
	@SendTo("/topic/newMessage")
	public ResponseEntity<List<MessageModel>> save(MessageModel chatMessageModel) {
		MessageModel chatMessage = new MessageModel(chatMessageModel.getText(), chatMessageModel.getAuthor(),
				new Date());
		MessageModel message = chatMessageRepository.save(chatMessage);
		List<MessageModel> chatMessageModelList = chatMessageRepository
				.findAll(new PageRequest(0, 15, Sort.Direction.DESC, "createDate")).getContent();
		return new ResponseEntity<List<MessageModel>>(chatMessageModelList, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/messages", method = RequestMethod.GET)
	public HttpEntity list() {
		List<MessageModel> chatMessageModelList = chatMessageRepository
				.findAll(new PageRequest(0, 5, Sort.Direction.DESC, "createDate")).getContent();
		return new ResponseEntity(chatMessageModelList, HttpStatus.OK);
	}
}
