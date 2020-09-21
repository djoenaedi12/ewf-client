package gasi.ewf.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gasi.ewf.entity.Role;
import gasi.ewf.repository.RoleRepository;


@Service
@Transactional(rollbackOn = {Exception.class})
public class RoleServiceImp implements RoleService{

	@Autowired
	RoleRepository roleRepository;
	
	@Override
	public Optional<Role> findByName(String name) {
		return roleRepository.findByName(name);
	}

	@Override
	public Optional<Role> findById(Long id) {
		return roleRepository.findById(id);
	}

}
