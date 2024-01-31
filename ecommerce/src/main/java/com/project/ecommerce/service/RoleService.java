package com.project.ecommerce.service;

import com.project.ecommerce.model.Role;

public interface RoleService {

	public Role createNewRole(Role role);
	
	public com.project.ecommerce.entities.Role convertToEntity(Role role); 
	
	public Role convertToModel(com.project.ecommerce.entities.Role role); 

}
