package com.santa.service;

import com.santa.dao.UserDAO;
import com.santa.dto.LoginDTO;
import com.santa.exception.BadParameterException;
import com.santa.exception.InvalidLoginException;
import com.santa.model.User;

public class LoginService {

	private UserDAO userDao;
	
	public LoginService() {
		this.userDao = new UserDAO();
	}
	
	public User login(LoginDTO loginDTO) throws BadParameterException, InvalidLoginException {
		if (loginDTO.getUsername().trim().equals("") && loginDTO.getPassword().trim().equals("")) {
			throw new BadParameterException("Username and password cannot be blank");
		}
		
		if (loginDTO.getUsername().trim().equals("")) {
			throw new BadParameterException("Username cannot be blank");
		}
		
		if (loginDTO.getPassword().trim().equals("")) {
			throw new BadParameterException("Password cannot be blank");
		}
		
		User user = userDao.getUserByUsernameAndPassword(loginDTO.getUsername(), loginDTO.getPassword());
		
		if (user == null) {
			throw new InvalidLoginException("You provided incorrect credentials when attempting to log in");
		}
		
		return user;
	}
	
}
