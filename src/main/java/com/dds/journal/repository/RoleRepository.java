package com.dds.journal.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dds.journal.domain.Role;

@Repository("roleRepository")
public interface RoleRepository extends CrudRepository<Role, Long>{
	
	Role findByRole(String role);
	
	/*@Query("select ur.role from user_role ur, User user where user.username=?1 and ur.userid=user.userid")
	List<String> findRoleByUsername(String username);*/
}
