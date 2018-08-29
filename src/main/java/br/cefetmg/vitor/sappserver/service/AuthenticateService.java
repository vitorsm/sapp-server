package br.cefetmg.vitor.sappserver.service;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.cefetmg.vitor.sappserver.dao.Filter;
import br.cefetmg.vitor.sappserver.dao.impl.UserDAO;
import br.cefetmg.vitor.sappserver.exceptions.DAOException;
import br.cefetmg.vitor.sappserver.exceptions.PermissionException;
import br.cefetmg.vitor.sappserver.facade.SAPPFacade;
import br.cefetmg.vitor.sappserver.models.User;
import br.cefetmg.vitor.sappserver.security.MD5PasswordEncoder;

@Service
public class AuthenticateService implements AuthenticationManager {

	private static final Logger LOGGER = Logger.getLogger(AuthenticateService.class.toString());
	
	@Autowired
	private SAPPFacade sf;
	
	@Autowired
	private UserDAO dao;
	
	@Autowired
	private PasswordEncoder encoder;
	
	private boolean auth(User user) throws DAOException {
		String login = user.getLogin();
		String password = user.getPassword();
		
		Filter<String> fLogin = new Filter<String>("login", login);
		Filter<String> fPassword = new Filter<String>("password", password);
		
		List<User> list = dao.get(Arrays.asList(fLogin, fPassword));

		user.setPassword(null);
		if (list != null && list.size() > 0) {
			user.setName(list.get(0).getName());
			return true;
		}
		
		return false;
	}
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		String password = "";
		
		MD5PasswordEncoder encoder = new MD5PasswordEncoder();
		
		if (authentication.getCredentials() != null) {
			password = encoder.encode(authentication.getCredentials().toString());
			
			User user = (User) authentication.getPrincipal();
			
			user.setPassword(password);
			System.out.println(user);
			try {
				if (auth(user)) {
					return authentication;
				}	
			} catch (DAOException ex) {
			}
			
		}
		
		throw new AuthenticationCredentialsNotFoundException("Bad credentials");
		
	}
	
	public User currentAccount() throws DAOException {
		String subject = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		try {
			return sf.userService.findByLogin(subject, true);
		} catch (PermissionException e) {
			e.printStackTrace();
			
			return null;
		}
	}
}
