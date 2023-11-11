package com.project.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.ecommerce.model.Role;
import com.project.ecommerce.service.RoleService;

@RestController
@CrossOrigin
public class RoleController {
	
	@Autowired
	private RoleService rolesService;
	
	@PostMapping("/createNewRole")
	public Role createNewRole(@RequestBody Role role) {
		return rolesService.createNewRole(role);
	}

}
