package com.dds.journal.service;

import com.dds.journal.domain.User;

public interface UserService {
	
	public Iterable<User> getUsers();
	public User getUser(Long id);
	public User saveUser(User user);
	public void deleteUser(User user);
	public User findByEmail(String email);
	public User findByUsername(String username);
	public User findByConfirmationToken(String confirmationToken);
	public Iterable<User> findOnlineUsers(String username);
}
