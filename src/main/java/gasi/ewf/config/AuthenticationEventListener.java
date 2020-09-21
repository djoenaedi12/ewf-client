package gasi.ewf.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;

import gasi.ewf.entity.User;
import gasi.ewf.entity.UserAttempt;
import gasi.ewf.service.UserAttempService;
import gasi.ewf.service.UserService;

@Component
public class AuthenticationEventListener {

	@Autowired
	UserService userService;
	
	@Autowired
	UserAttempService userAttempService;
	
    @EventListener
    public void authenticationFailed(AuthenticationFailureBadCredentialsEvent event) throws Exception {
        String username = (String) event.getAuthentication().getPrincipal();
        User user = userService.findByUserName(username).orElse(null);
        if(user != null && !user.isLocked()) {
        	UserAttempt userAttemp = userAttempService.findByUser(user).orElse(null);
        	if(userAttemp == null) {
        		userAttemp = new UserAttempt();
        	}
        	
        	userAttemp.setAttempts(userAttemp.getAttempts()+1);
        	userAttemp.setUser(user);
        	userAttempService.save(userAttemp);
        	
        	if(userAttemp.getAttempts() >= 3) {
        		user.setLocked(true);
        		userService.save(user);
        	}
        }
    }

}