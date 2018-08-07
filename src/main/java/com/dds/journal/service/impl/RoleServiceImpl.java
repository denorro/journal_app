package com.dds.journal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dds.journal.domain.Role;
import com.dds.journal.repository.RoleRepository;
import com.dds.journal.service.RoleService;

@Service("roleService")
public class RoleServiceImpl implements RoleService{

	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public Iterable<Role> getRoles() {
		return roleRepository.findAll();
	}

	@Override
	public Role findById(Long id) {
		return roleRepository.findOne(id);
	}

	@Override
	public Role saveRole(Role role) {
		return roleRepository.save(role);
	}

	@Override
	public void deleteRole(Role role) {
		roleRepository.delete(role);
	}

	@Override
	public void deleteRoles() {
		roleRepository.deleteAll();
	}

	@Override
	public Role findByRole(String role) {
		roleRepository.findByRole(role);
		return null;
	}
}
