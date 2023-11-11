package com.project.ecommerce.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ecommerce.entities.Role;
import com.project.ecommerce.model.User;
import com.project.ecommerce.repository.UserRepository;
import com.project.ecommerce.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User registerNewUser(User user) {
		com.project.ecommerce.entities.User UserEntity = userRepository.save(convertToEntity(user));
		return convertToModel(UserEntity);	
	}

	@Override
	public com.project.ecommerce.entities.User convertToEntity(User user) {
		com.project.ecommerce.entities.User userEntity = new com.project.ecommerce.entities.User();
		userEntity.setUsername(user.getName());
		userEntity.setUserFirstName(user.getFirstName());
		userEntity.setUserLastName(user.getLastName());
		userEntity.setUserPassword(user.getPassword());
		
		Set<Role> roles = new HashSet<>();
		
		user.getRoles().forEach(role -> {
			Role roleEntity = new Role(role.getName(),role.getDesc());	
			roles.add(roleEntity);
		});
		userEntity.setRoles(roles);
		return userEntity;
	}

	@Override
	public User convertToModel(com.project.ecommerce.entities.User user) {
		User userModel = new User();
		userModel.setName(user.getUsername());
		userModel.setFirstName(user.getUserFirstName());
		userModel.setLastName(user.getUserLastName());
		userModel.setPassword(user.getUserPassword());
		
		Set<com.project.ecommerce.model.Role> roles = new HashSet<>();
		
		user.getRoles().forEach(role -> {
			com.project.ecommerce.model.Role roleModel = new com.project.ecommerce.model.Role(role.getRoleName(),role.getRoleDesc());	
			roles.add(roleModel);
		});
		userModel.setRoles(roles);
		return userModel;
	}

}
