package com.mosofty.crm.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mosofty.crm.dto.CompteBancaireDto;
import com.mosofty.crm.dto.UserForFacturation;
import com.mosofty.crm.service.ParametersUserService;
import com.mosofty.crm.tools.AuthentificationManager;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "ParameterUser")
@RestController
@RequestMapping(path = "api/parameter/user")
@RequiredArgsConstructor
public class ParameterUserApi {

	@Autowired
	private ParametersUserService parameterService;
	@Autowired
	private AuthentificationManager authentificationManager;
	
	
	@PutMapping("update-compte")
	public ResponseEntity<?> update(@RequestBody @Valid CompteBancaireDto request) {
		String principal = authentificationManager.getPrincipal();
		 
		parameterService.updateCompteForUser(request,principal);

		return ResponseEntity.ok().build();
	}
	
	@GetMapping("user-for-facturation")
	public ResponseEntity<UserForFacturation> getUserForFacturation()
	{
		String principal = authentificationManager.getPrincipal();
		UserForFacturation userForFacturation = parameterService.getUserForFacturation(principal);
		return ResponseEntity.ok().body(userForFacturation);
	}

}
