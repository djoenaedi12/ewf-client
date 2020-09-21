package gasi.ewf.service;

import java.util.Optional;

import gasi.ewf.entity.User;
import gasi.ewf.entity.UserAttempt;


public interface UserAttempService {

	Optional<UserAttempt> findByUser(User user);
	UserAttempt save(UserAttempt userAttemp);
}
