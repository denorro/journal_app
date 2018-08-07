package com.dds.journal.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dds.journal.domain.User;

@Repository("userRepository")
public interface UserRepository extends CrudRepository<User, Long>{

	User findByUsername(String username);
	
	User findByEmail(String email);
	
	User findByConfirmationToken(String confirmationToken);
	
	/*@Query("select u from User u where u.username like '%:search%' OR u.firstname like '%:search%' OR u.lastname like '%:search%' ")
	User searchForUsers(@Param("search") String search);*/
	
	//@Query("Select u from User u where u.username != ':username' and u.status = 'online'")
	//Iterable<User> findOnlineUsers(@Param("username") String username);
}
