package com.santa.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.santa.dto.MessageDTO;
import com.santa.dto.ReimbursementDTO;
import com.santa.model.User;
import com.santa.model.Reimbursement;
import com.santa.service.ReimbursementService;

import io.javalin.Javalin;
import io.javalin.http.Handler;

public class ReimbursementController implements Controller {

	private ReimbursementService reimbursementService;
	
	public ReimbursementController() {
		this.reimbursementService = new ReimbursementService();
	}
	
	private Handler getAllReimbursements = (ctx) -> {
		HttpSession session = ctx.req.getSession();
		
		if (session.getAttribute("currentUser") == null) {
			ctx.json(new MessageDTO("You need to be logged in to perform this action"));
		} else {
			User currentUser = (User) session.getAttribute("currentUser");
			
			if (currentUser.getId() == 1) {
				List<Reimbursement> reimbursements = reimbursementService.getAllReimbursements();
				
				ctx.json(reimbursements);
				ctx.status(200);
			} else {
				ctx.json(new MessageDTO("You are not a financial manager."));
				ctx.status(401);
			}
		}
		
	};
	
	private Handler getAllReimbursementsByStatus = (ctx) -> {
		HttpSession session = ctx.req.getSession();
		
		if (session.getAttribute("currentUser") == null) {
			ctx.json(new MessageDTO("You need to be logged in to perform this action"));
		} else {
			User currentUser = (User) session.getAttribute("currentUser");
			
			String reimStatus = ctx.pathParam("status");
			
			if (currentUser.getId() == 1) {
				List<Reimbursement> reimbursements = reimbursementService.getAllReimbursementsByStatus(reimStatus);
				
				ctx.json(reimbursements);
				ctx.status(200);
			} else {
				ctx.json(new MessageDTO("You are not the user that you want to retrieve all users from"));
				ctx.status(401);
			}
		}
		
	};
	
	private Handler getAllReimbursementsBelongingToSpecificUser = (ctx) -> {
		HttpSession session = ctx.req.getSession();
		
		if (session.getAttribute("currentUser") == null) {
			ctx.json(new MessageDTO("You need to be logged in to perform this action"));
		} else {
			User currentUser = (User) session.getAttribute("currentUser");
			
			String userId = ctx.pathParam("userid");
			
			if (currentUser.getId() == Integer.parseInt(userId)) {
				List<Reimbursement> reimbursements = reimbursementService.getAllReimbursementsFromUserId(userId);
				
				ctx.json(reimbursements);
				ctx.status(200);
			} else {
				ctx.json(new MessageDTO("You are not the user that you want to retrieve all users from"));
				ctx.status(401);
			}
		}
		
	};
	
	private Handler addReimbursement = (ctx) -> {
		HttpSession session = ctx.req.getSession();

		if (session.getAttribute("currentUser") == null) {
			ctx.json(new MessageDTO("You need to log in first"));
		} else {
			User currentUser = (User) session.getAttribute("currentUser");


			ReimbursementDTO addReimbursement = ctx.bodyAsClass(ReimbursementDTO.class);

			String userId = ctx.pathParam("userid");
			
			
			if (currentUser.getId() == Integer.parseInt(userId)) {
				
				Reimbursement addedReimbursement = reimbursementService.addReimbursement(addReimbursement, userId);
		
				
				ctx.json(addedReimbursement);
				ctx.status(200);
			} else {
				ctx.json(new MessageDTO("You are not the authorized user to add a Reimbursement for thi account."));
			}
		}
	};
	
	private Handler editReimbursement = (ctx) -> {
		HttpSession session = ctx.req.getSession();
		
		String reimbId = ctx.pathParam("reimbId");
		String reimbStatus = ctx.pathParam("reimbStatus");

		User currentUser = (User) session.getAttribute("currentUser");
		
		Reimbursement editedReimbursement = reimbursementService.editReimbursement(currentUser, reimbId, reimbStatus);
		ctx.json(editedReimbursement);
		
	};
	
	@Override
	public void mapEndpoints(Javalin app) {
		app.patch("/reimbursement/:reimbId/:reimbStatus", editReimbursement);
		app.get("/user/:userid/reimbursement", getAllReimbursementsBelongingToSpecificUser);
		app.post("/user/:userid/reimbursement", addReimbursement);
		app.get("/reimbursement", getAllReimbursements);
		app.get("/reimbursement/:status", getAllReimbursementsByStatus);
	}

}
