package com.spring.boot.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

public class SecureUserDetails implements UserDetails {
	private AccountCredentials accountCredentials;

	/**
	 * @return the accountCredentials
	 */
	public AccountCredentials getAccountCredentials() {
		return accountCredentials;
	}

	public SecureUserDetails(AccountCredentials accountCredentials) {
		this.accountCredentials = accountCredentials;
	}

	/**
	 * @param accountCredentials
	 *            the accountCredentials to set
	 */
	public void setAccountCredentials(AccountCredentials accountCredentials) {
		this.accountCredentials = accountCredentials;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}
}
