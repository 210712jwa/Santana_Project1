package com.santa.dao;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.santa.model.User;
import com.santa.util.SessionFactorySingleton;

public class UserDAO {

	public User getUserByUsernameAndPassword(String username, String password) {
		SessionFactory sf = SessionFactorySingleton.getSessionFactory();
		Session session = sf.openSession();

		try {
			User user = (User) session.createQuery("FROM User u WHERE u.username = :username AND u.password = :password")
					.setParameter("username", username)
					.setParameter("password", password)
					.getSingleResult();

			return user;
		} catch(NoResultException e) {			
			return null;
		} finally {
			session.close();
		}
	}

	public User getUserById(int userid) {
		SessionFactory sf = SessionFactorySingleton.getSessionFactory();
		Session session = sf.openSession();

		try {
			User user = (User)session.createQuery("FROM User u WHERE id = userid")
					.setParameter("userid", userid).getSingleResult();
			return user;
		}catch(NoResultException e) {
			return null;
		}finally {
			session.close();
		}
	}

}
