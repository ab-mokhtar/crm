package com.mosofty.crm.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "compte")
 @Data
 @Builder
 @NoArgsConstructor
 @AllArgsConstructor
public class CompteBancaire {
	 @Id
	  @GeneratedValue (strategy = GenerationType.AUTO)
	  private Long id;
	 
	 private String rib;
	 
	 private String bic;
	 
	 private String iban;
	 
	 	
	 private String compte;
	 
	 
	 
	
	 @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "id_user")
	 private User user;
	 
	 private Boolean isEnable;
}
