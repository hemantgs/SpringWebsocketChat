package com.spring.boot.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.spring.boot.repository.UserRepository;

@Component
public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AccountCredentials cred = userRepository.findByUsername(username);
		if (cred == null) {
			throw new UsernameNotFoundException(username);
		} else {
			UserDetails details = new SecureUserDetails(cred);
			return details;

		}
	}
}
