package com.project.ecommerce;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.project.ecommerce.model.Role;
import com.project.ecommerce.model.User;
import com.project.ecommerce.service.UserService;

@SpringBootApplication
public class EcommerceApplication implements CommandLineRunner {

	@Autowired
	private UserService userService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		try {
		System.out.println("starting code");
	
		User user=new User();
		user.setFirstName("Hirak");
		user.setLastName("Babariya");
		user.setName("HirakBabariya");
		user.setPassword(this.bCryptPasswordEncoder.encode("hirak@123"));
		
		Role role=new Role();
		role.setName("Admin");
		role.setDesc("Admin Role");
		
		Set<Role> userRoleSet = new HashSet<>();
		userRoleSet.add(role);
		user.setRoles(userRoleSet);
		
		User user1= this.userService.registerNewUser(user);
		System.out.println(user1.getName());
		}catch(Exception e ) {
			e.printStackTrace();
		}
	}


}
