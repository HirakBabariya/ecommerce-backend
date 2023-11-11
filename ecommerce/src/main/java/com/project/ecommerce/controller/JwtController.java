package com.project.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.ecommerce.model.JwtRequest;
import com.project.ecommerce.model.JwtResponse;
import com.project.ecommerce.service.impl.JwtServiceImpl;

@RestController
@CrossOrigin
public class JwtController {

	@Autowired
	private JwtServiceImpl jwtServiceImpl;
	
	@PostMapping("/authenticate")
	public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception {
		return jwtServiceImpl.createJwtToken(jwtRequest);
		
	}
}
