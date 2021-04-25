package com.vtt.apps.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class HelloController {
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@GetMapping("/hello")
	public String wish() {
		LOGGER.info("INFO-wish");
		LOGGER.warn("WARN-wish");
		LOGGER.trace("TRACE-wish");
		LOGGER.error("ERROR-wish");
		LOGGER.debug("DEBUG-wish");
		return "Hello";
	}

}
