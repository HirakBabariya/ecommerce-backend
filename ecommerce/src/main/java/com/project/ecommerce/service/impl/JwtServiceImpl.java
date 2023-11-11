package com.project.ecommerce.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.ecommerce.entities.User;
import com.project.ecommerce.model.JwtRequest;
import com.project.ecommerce.model.JwtResponse;
import com.project.ecommerce.repository.UserRepository;
import com.project.ecommerce.service.UserService;
import com.project.ecommerce.utill.JwtUtill;

@Service
public class JwtServiceImpl implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private JwtUtill jwtUtill;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserService userService;
	
	public JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception{
		String username = jwtRequest.getUsername();
		String password = jwtRequest.getPassword();
		authenticate(username, password);
		final UserDetails userDetails = loadUserByUsername(username);
		String token = jwtUtill.generateToken(userDetails);
		User user = userRepository.findById(username).get();
		return new JwtResponse(userService.convertToModel(user), token); 
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findById(username).get();
		if(user != null) {
			return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getUserPassword(), getAuthorities(user));
		}else {
			throw new UsernameNotFoundException("Username does not exist!!");
		}
	}
	
	private Set getAuthorities(User user) {
		Set authorities = new HashSet<>();
		user.getRoles().forEach(role -> {
			authorities.add(new SimpleGrantedAuthority("ROLE_"+role));
		});
		return authorities;
	}
	
	private void authenticate(String username, String password) {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));			
		} catch (DisabledException e) {
			System.out.println("User is Disabled");		
		} catch (BadCredentialsException e) {
			System.out.println("Bad Credentials");		
		}
	}

}
