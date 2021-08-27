package com.santa.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.santa.dto.MessageDTO;
import com.santa.exception.BadParameterException;
import com.santa.exception.InvalidLoginException;

import io.javalin.Javalin;
import io.javalin.http.ExceptionHandler;

public class ExceptionController implements Controller {

	private Logger logger = LoggerFactory.getLogger(ExceptionController.class);
	
	private ExceptionHandler<BadParameterException> badParameterExceptionHandler = (e, ctx) -> {
		logger.info("Exception Occurred: Exception message is " + e.getMessage());
		
		ctx.status(400);
		ctx.json(new MessageDTO(e.getMessage()));
	};
	
	private ExceptionHandler<InvalidLoginException> invalidLoginExceptionHandler = (e, ctx) -> {
		logger.info("Exception Occurred: Exception message is " + e.getMessage());
		
		ctx.status(400);
		ctx.json(new MessageDTO(e.getMessage()));
	};
	
	@Override
	public void mapEndpoints(Javalin app) {
		app.exception(BadParameterException.class, badParameterExceptionHandler);
		app.exception(InvalidLoginException.class, invalidLoginExceptionHandler);
	}
	
}
