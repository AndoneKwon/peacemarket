package com.hanimum.gateway.security;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hanimum.gateway.model.User;
import com.hanimum.gateway.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException{
		
		User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail).orElseThrow(
				() -> new UsernameNotFoundException("User not found with user name or email" + usernameOrEmail));
		
		return UserPrincipal.create(user);
		
	}
	
	@Transactional
	public UserDetails loadUserById(Long id) {
		User user = userRepository.findById(id)
				.orElseThrow(()-> new UsernameNotFoundException("User not found with id : " +id));
		
		return UserPrincipal.create(user);
	}
	
}
