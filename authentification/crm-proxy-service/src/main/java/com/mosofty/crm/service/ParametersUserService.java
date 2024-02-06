package com.mosofty.crm.service;

import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Service;

import com.mosofty.crm.dto.CompteBancaireDto;
import com.mosofty.crm.dto.UserForFacturation;
import com.mosofty.crm.model.CompteBancaire;
import com.mosofty.crm.model.User;
import com.mosofty.crm.repository.UserRepo;

import lombok.RequiredArgsConstructor;

@Service

@RequiredArgsConstructor
public class ParametersUserService {

	private final UserRepo userRepo;
	
	
	public void updateCompteForUser(@NotNull @Valid CompteBancaireDto compteBancaire, @NotNull String principal)
	{
		Optional<User> userOptional = userRepo.findById(Long.valueOf(principal));
		if(userOptional.isPresent())
		{
			User user = userOptional.get();
			CompteBancaire compte = CompteBancaire.builder().bic(compteBancaire.getBic())
							.compte(compteBancaire.getCompte())
							.iban(compteBancaire.getIban())
							.rib(compteBancaire.getRib())
							.isEnable(true)
							.user(user).build();
			user.updateCompte(compte);
			userRepo.save(user);
		}
	}
	
	
	public UserForFacturation getUserForFacturation( @NotNull String principal)
	{
		Optional<User> userOptional = userRepo.findById(Long.valueOf(principal));
		if(userOptional.isPresent())
		{
			User user = userOptional.get();
			CompteBancaire compte = user.getCompteBancaires().stream().filter(compt -> compt.getIsEnable()).findFirst().orElse(null);
			return UserForFacturation.builder()
			.bic(compte.getBic())
			.compte(compte.getCompte())
			.iban(compte.getIban())
			.rib(compte.getRib())
			.fullName(user.getFullName())
			.username(user.getUsername())
			.surname(user.getSurname())
			.companyName(user.getCompanyName()).build();
			
							
		}
		
		return null;
	}
}
