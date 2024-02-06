package com.mosofty.crm.tools;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthentificationManager {

	
	
	
	public String getPrincipal()
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		   String currentUserName = null;
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
		      currentUserName = authentication.getName();		   
		}
		return (currentUserName.split(","))[0];
	}
}
