package com.mosofty.crm.dto;

import lombok.Data;

@Data
public class CompteBancaireDto {

	private String compte;
	private String rib;
	private String iban;
	private String bic;

}
