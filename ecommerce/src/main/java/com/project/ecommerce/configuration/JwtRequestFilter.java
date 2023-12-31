package com.project.ecommerce.configuration;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.project.ecommerce.service.impl.JwtServiceImpl;
import com.project.ecommerce.utill.JwtUtill;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter{

	@Autowired
	private JwtUtill jwtUtill;
	
	@Autowired
	private JwtServiceImpl jwtServiceImpl;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		final String header = request.getHeader("Authorization");
		String jwtToken = null;
		String userName = null;
		
		if(header != null && header.startsWith("Bearer ")) {
			jwtToken = header.substring(7);
			
			try {
				userName = jwtUtill.getUserNameFromToken(jwtToken);
			} catch (IllegalArgumentException e) {
				System.out.println("Enable to get JWT Token");
			} catch (ExpiredJwtException e) {
				System.out.println("JWT Token is Expired");
			}
		}else {
			System.out.println("JWT Token does not start with Bearer");
		}
		
		if(userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = jwtServiceImpl.loadUserByUsername(userName);
			
			if(jwtUtill.validateToken(jwtToken, userDetails)) {
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =  new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);	
			}
		} else {
			System.out.println("JWT Token is not valid");
		}
		filterChain.doFilter(request, response);
	}

}
