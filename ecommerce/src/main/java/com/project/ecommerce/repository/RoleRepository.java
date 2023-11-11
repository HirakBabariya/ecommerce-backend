package com.project.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.ecommerce.entities.Role;

public interface RoleRepository extends JpaRepository<Role, String>{

}
