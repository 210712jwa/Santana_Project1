package com.santa.app;

import com.santa.controller.Controller;
import com.santa.controller.ExceptionController;
import com.santa.controller.LoginController;
import com.santa.controller.ReimbursementController;

import io.javalin.Javalin;

public class Application {

	private static Javalin app;
	
	public static void main(String[] args) {
		app = Javalin.create((config) -> {
			config.enableCorsForAllOrigins();
		});
		
		mapControllers(new LoginController(), new ExceptionController(), new ReimbursementController());
		
		app.start(7000);
	}
	
	private static void mapControllers(Controller... controllers) {
		for (Controller c : controllers) {
			c.mapEndpoints(app);
		}
	}

}
