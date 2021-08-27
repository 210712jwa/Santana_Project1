package com.santa.service;

import java.util.List;

import com.santa.dao.ReimbursementDAO;
import com.santa.dto.ReimbursementDTO;
import com.santa.model.Reimbursement;
import com.santa.model.User;

import io.javalin.http.Handler;

public class ReimbursementService {

	private ReimbursementDAO reimbursementDao;
	
	public ReimbursementService() {
		this.reimbursementDao = new ReimbursementDAO();
	}
	
	public List<Reimbursement> getAllReimbursementsFromUserId(String userId) {
		int id = Integer.parseInt(userId);
		
		List<Reimbursement> reimbursements = reimbursementDao.getAllReimbursementsFromUserId(id);
		
		return reimbursements;
	}

	public Reimbursement addReimbursement(ReimbursementDTO reimbursement, String userId) {
		
		int id = Integer.parseInt(userId);
	
		Reimbursement addedReimbursement = reimbursementDao.addReimbursement(reimbursement,id);
		
		return addedReimbursement;
	
}

	public List<Reimbursement> getAllReimbursements() {

		List<Reimbursement> reimbursements = reimbursementDao.getAllReimbursements();
		
		return reimbursements;
	}

	public List<Reimbursement> getAllReimbursementsByStatus(String reimStatus) {
		
		List<Reimbursement> reimbursements = reimbursementDao.getAllReimbursementsByStatus(reimStatus);
		
		return reimbursements;
	}

	public Reimbursement editReimbursement(User currentUser, String reimbId, String reimbStatus) {
		
		int id = Integer.parseInt(reimbId);
		int statusId = Integer.parseInt(reimbStatus);
	
		Reimbursement addedReimbursement = reimbursementDao.editReimbursement(currentUser, id, statusId);
		
		return addedReimbursement;
	}
	
}
