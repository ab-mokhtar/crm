package com.mosofty.crm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserForFacturation {

	private String fullName;
	private String username;
	private String surname;
	private String email;
	private String compte;
	private String rib;
	private String iban;
	private String bic;
	private String companyName;
}
