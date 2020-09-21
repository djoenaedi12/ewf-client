package gasi.ewf.service;

import java.util.Optional;

import gasi.ewf.entity.Role;


public interface RoleService {

	Optional<Role> findByName(String name);
	Optional<Role> findById(Long id);
}
