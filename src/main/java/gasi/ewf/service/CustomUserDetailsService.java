package gasi.ewf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gasi.ewf.entity.User;
import gasi.ewf.entity.UserPrincipal;
import gasi.ewf.repository.UserRepository;


@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
    UserRepository userRepository;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail).orElse(null);
        
        if(user == null) {
        	throw new UsernameNotFoundException("Username not found");
        } else if (user.isLocked()) {
        	throw new UsernameNotFoundException("User is locked");
        } else {
        	return UserPrincipal.create(user);
        }
	}

}
