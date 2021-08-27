package com.santa.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.santa.model.Reimbursement;
import com.santa.model.ReimbursementStatus;
import com.santa.model.ReimbursementType;
import com.santa.model.User;
import com.santa.model.UserRole;

public class PopulateDataInDatabase {

	public static void main(String[] args) {
		populateReimbursementStatus_ReimbursementType_UserRole();
		addSampleUsers();
		
		addReimbursementsUsers987();
	}

	private static void populateReimbursementStatus_ReimbursementType_UserRole() {
		SessionFactory sf = SessionFactorySingleton.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		
		UserRole admin = new UserRole("admin");
		UserRole user = new UserRole("user");
		session.persist(admin);
		session.persist(user);
		
		ReimbursementStatus pending = new ReimbursementStatus("pending");
		ReimbursementStatus approved = new ReimbursementStatus("approved");
		ReimbursementStatus denied = new ReimbursementStatus("denied");
		session.persist(pending);
		session.persist(approved);
		session.persist(denied);
		
		ReimbursementType lodging = new ReimbursementType("lodging");
		ReimbursementType travel = new ReimbursementType("travel");
		ReimbursementType food = new ReimbursementType("food");
		ReimbursementType other = new ReimbursementType("other");
		session.persist(lodging);
		session.persist(travel);
		session.persist(food);
		session.persist(other);
		
		tx.commit();
		session.close();
	}
	
	private static void addSampleUsers() {
		SessionFactory sf = SessionFactorySingleton.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		
		User adminUser1 = new User("adminUsername", "adminPassword", "adminFName", "adminLName", "admin@admin.com");
		UserRole admin = (UserRole) session.createQuery("FROM UserRole ur WHERE ur.userRole = 'admin'").getSingleResult();
		adminUser1.setUserRole(admin);
		session.persist(adminUser1);
		
		UserRole user = (UserRole) session.createQuery("FROM UserRole ur WHERE ur.userRole = 'user'").getSingleResult();
		User regularUser1 = new User("eUsername1", "ePassword1", "eFName1", "eLName1", "e1@e1.com");
		regularUser1.setUserRole(user);
		User regularUser2 = new User("eUsername2", "ePassword2", "eFName2", "eLName2", "e2@e2.com");
		regularUser2.setUserRole(user);
		
		session.persist(regularUser1);
		session.persist(regularUser2);
		
		tx.commit();
		session.close();
	}
	
	private static void addReimbursementsUsers987() {
		SessionFactory sf = SessionFactorySingleton.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		
		User aUser = (User) session.createQuery("FROM User u WHERE u.username = 'adminUsername'").getSingleResult();
		ReimbursementStatus pending = (ReimbursementStatus) session.createQuery("FROM ReimbursementStatus s WHERE s.status = 'pending'").getSingleResult();
		
		ReimbursementType lodging = (ReimbursementType) session.createQuery("FROM ReimbursementType s WHERE s.type = 'lodging'").getSingleResult();
		ReimbursementType travel = (ReimbursementType) session.createQuery("FROM ReimbursementType s WHERE s.type = 'travel'").getSingleResult();
		ReimbursementType food = (ReimbursementType) session.createQuery("FROM ReimbursementType s WHERE s.type = 'food'").getSingleResult();
		ReimbursementType other = (ReimbursementType) session.createQuery("FROM ReimbursementType s WHERE s.type = 'other'").getSingleResult();
		
		Reimbursement reimbursement1 = new Reimbursement();
		reimbursement1.setAuthor(aUser);
		reimbursement1.setStatus(pending);
		reimbursement1.setType(lodging);
		
		Reimbursement reimbursement2 = new Reimbursement();
		reimbursement2.setAuthor(aUser);
		reimbursement2.setStatus(pending);
		reimbursement2.setType(travel);
		
		Reimbursement reimbursement3 = new Reimbursement();
		reimbursement3.setAuthor(aUser);
		reimbursement3.setStatus(pending);
		reimbursement3.setType(food);
		
		Reimbursement reimbursement4 = new Reimbursement();
		reimbursement4.setAuthor(aUser);
		reimbursement4.setStatus(pending);
		reimbursement4.setType(other);
		
		session.persist(reimbursement1);
		session.persist(reimbursement2);
		session.persist(reimbursement3);
		session.persist(reimbursement4);
		
		tx.commit();
		session.close();
	}

}
