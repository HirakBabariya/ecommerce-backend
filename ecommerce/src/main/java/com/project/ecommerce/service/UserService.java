package com.project.ecommerce.service;

import com.project.ecommerce.model.User;


public interface UserService {

	public User registerNewUser(User user);
	
	public com.project.ecommerce.entities.User convertToEntity(User user); 
	
	public User convertToModel(com.project.ecommerce.entities.User user); 
}
