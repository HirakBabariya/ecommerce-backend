package com.project.ecommerce.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ecommerce.model.Role;
import com.project.ecommerce.repository.RoleRepository;
import com.project.ecommerce.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	private RoleRepository roleRepository;
	
	public Role createNewRole(Role role) {
		 com.project.ecommerce.entities.Role roleEntity = roleRepository.save(convertToEntity(role));
		 return convertToModel(roleEntity);
	}

	@Override
	public com.project.ecommerce.entities.Role convertToEntity(Role role) {
		com.project.ecommerce.entities.Role roleEntity = new com.project.ecommerce.entities.Role(role.getName(),role.getDesc());
		return roleEntity;

	}

	@Override
	public Role convertToModel(com.project.ecommerce.entities.Role role) {
		Role roleModel = new Role(role.getRoleName(),role.getRoleDesc());
		return roleModel;
	}
}
