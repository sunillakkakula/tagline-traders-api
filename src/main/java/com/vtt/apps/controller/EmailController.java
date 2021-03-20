package com.vtt.apps.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vtt.apps.model.MailRequest;
import com.vtt.apps.model.MailResponse;
import com.vtt.apps.service.EmailService;

@RestController
@RequestMapping("/api")
public class EmailController {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private EmailService service;
	
	@PostMapping("/send-email")
	public MailResponse sendEmail(@RequestBody MailRequest request) {
		Map<String, Object> model = new HashMap<>();
		model.put("Name", request.getName());
		model.put("location", "Gachibowli,Hyderabad,India");
		return service.sendEmail(request, model);

	}

}
