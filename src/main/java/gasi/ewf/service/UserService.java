package gasi.ewf.service;

import java.util.List;
import java.util.Optional;

import gasi.ewf.entity.User;


public interface UserService {

	List<User> findAll();
	Optional<User> findById(Long id);
	Optional<User> findByUserName(String username);
	Optional<User> findByEmail(String email);
	User save(User user);
	void delete(User user);
	
}
