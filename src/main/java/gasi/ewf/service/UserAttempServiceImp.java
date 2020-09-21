package gasi.ewf.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gasi.ewf.entity.User;
import gasi.ewf.entity.UserAttempt;
import gasi.ewf.repository.UserAttempRepository;

@Service
public class UserAttempServiceImp implements UserAttempService{

	
	@Autowired
	UserAttempRepository userAttempRepository;
	
	@Override
	public Optional<UserAttempt> findByUser(User user) {
		return userAttempRepository.findByUser(user);
	}

	@Override
	public UserAttempt save(UserAttempt userAttemp) {
		return userAttempRepository.save(userAttemp);
	}
	
	

}
