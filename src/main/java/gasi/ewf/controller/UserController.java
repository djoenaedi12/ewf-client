package gasi.ewf.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import gasi.ewf.entity.Role;
import gasi.ewf.entity.User;
import gasi.ewf.request.UserRequest;
import gasi.ewf.service.RoleService;
import gasi.ewf.service.UserService;

@RestController
@RequestMapping("/api/v1")
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	RoleService roleService;

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public ResponseEntity<?> getAll() {
		List<User> list = userService.findAll();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@RequestMapping(value = "/user/{username}", method = RequestMethod.GET)
	public ResponseEntity<?> getByUsername(@PathVariable String username) throws Exception {
		User user = userService.findByUserName(username).orElseThrow(() -> new Exception("User with " + username + " not found"));
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public ResponseEntity<?> add(@Valid @RequestBody UserRequest request) throws NumberFormatException, Exception {
		User user = new User();
		Set<Role> roles = new HashSet<>();
		for (String roleId : request.getRole()) {
			Role role = roleService.findById(new Long(roleId))
					.orElseThrow(() -> new Exception("Role with id " + request.getRole() + " not found"));
			roles.add(role);
		}
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setUsername(request.getUsername());
		user.setEmail(request.getEmail());
		user.setName(request.getName());
		user.setPassword(encoder.encode(request.getPassword()));
		user.setRoles(roles);
		userService.save(user);
		
		return new ResponseEntity<>(user, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/user/{username}", method = RequestMethod.DELETE)
	public ResponseEntity<?> delete(@PathVariable String username) throws Exception {
		User user = userService.findByUserName(username).orElseThrow(() -> new Exception("User with " + username + " not found"));
		userService.delete(user);
		
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@RequestMapping(value = "/user/{username}", method = RequestMethod.PUT)
	public ResponseEntity<?> update(@PathVariable String username, @Valid @RequestBody UserRequest request) throws Exception {
		User user = userService.findByUserName(username).orElseThrow(() -> new Exception("User with " + username + "not found"));
		Set<Role> roles = new HashSet<>();
		for (String roleId : request.getRole()) {
			Role role = roleService.findById(new Long(roleId))
					.orElseThrow(() -> new Exception("Role with id " + request.getRole() + " not found"));
			roles.add(role);
		}
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setUsername(request.getUsername());
		user.setEmail(request.getEmail());
		user.setName(request.getName());
		user.setPassword(encoder.encode(request.getPassword()));
		user.setRoles(roles);
		userService.save(user);
		
		return new ResponseEntity<>(user, HttpStatus.CREATED);
	}
}
