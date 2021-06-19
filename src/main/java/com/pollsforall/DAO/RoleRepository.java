package com.pollsforall.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pollsforall.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
