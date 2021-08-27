package com.santa.dao;

import java.sql.Blob;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.santa.dto.ReimbursementDTO;
import com.santa.model.*;
import com.santa.util.SessionFactorySingleton;

public class ReimbursementDAO {

	public List<Reimbursement> getAllReimbursementsFromUserId(int id) {
		SessionFactory sf = SessionFactorySingleton.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		
		List<Reimbursement> reimbursements = session.createQuery("SELECT s FROM Reimbursement s JOIN s.author u WHERE u.id = :userid").setParameter("userid", id).getResultList();
	
		return reimbursements;
	}

	public Reimbursement addReimbursement(ReimbursementDTO regularReimbursement, int id) {
		Date date = new Date();
		Timestamp timestamp = new Timestamp(date.getTime());
		Blob receipt = null;
		
		SessionFactory sf = SessionFactorySingleton.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		
		Reimbursement addedReimbursement = new Reimbursement();
		addedReimbursement.setAuthor(session.get(User.class, id));
		addedReimbursement.setDescription(regularReimbursement.getReimDescription());
		addedReimbursement.setStatus(session.get(ReimbursementStatus.class, 1));
		addedReimbursement.setAmount(regularReimbursement.getReimAmount());
		addedReimbursement.setResolver(null);
		addedReimbursement.setSubmitted(timestamp);
		addedReimbursement.setResolved(null);
		addedReimbursement.setReceipt(receipt);
		addedReimbursement.setType(session.get(ReimbursementType.class, regularReimbursement.getReimType()));
		session.persist(addedReimbursement);
		
		
		tx.commit();
		session.close();
		
		return addedReimbursement;
	}

	public List<Reimbursement> getAllReimbursements() {
		SessionFactory sf = SessionFactorySingleton.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		
		List<Reimbursement> reimbursements = session.createQuery("SELECT s FROM Reimbursement s").getResultList();
		return reimbursements;
	}

	public List<Reimbursement> getAllReimbursementsByStatus(String reimbStatus) {
		SessionFactory sf = SessionFactorySingleton.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		
		List<Reimbursement> reimbursements = session.createQuery("SELECT s FROM Reimbursement s JOIN s.status u WHERE u.status = :reimbStatus").setParameter("reimbStatus", reimbStatus).getResultList();
	
		return reimbursements;
	}

	public Reimbursement editReimbursement(User currentUser, int id, int reimbStatus) {
		
		Date date = new Date();
		Timestamp resolved = new Timestamp(date.getTime());
		
		SessionFactory sf = SessionFactorySingleton.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		
		Reimbursement editedReimbursement = session.get(Reimbursement.class, id);
		editedReimbursement.setResolver(currentUser);
		editedReimbursement.setResolved(resolved);
		editedReimbursement.setStatus(session.get(ReimbursementStatus.class, reimbStatus));
		session.saveOrUpdate(editedReimbursement);
		
		tx.commit();
		session.close();		
	
		return editedReimbursement;
	}
	
}
