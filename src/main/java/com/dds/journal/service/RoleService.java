package com.dds.journal.service;

import com.dds.journal.domain.Role;

public interface RoleService {
	
	public Iterable<Role> getRoles();
	public Role findById(Long id);
	public Role findByRole(String role);
	public Role saveRole(Role role);
	public void deleteRole(Role role);
	public void deleteRoles();
}
