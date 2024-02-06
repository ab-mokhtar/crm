package com.mosofty.crm.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

import com.mosofty.crm.dto.RoleView;


@Entity
@Table(name = "roles")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role implements GrantedAuthority {

  private static final long serialVersionUID = 1L;
  public static final String SUPER_ADMIN = "SUPER_ADMIN";
  public static final String ADMIN = "ADMIN";
  public static final String SIMPLE_USER = "SIMPLE_USER";
 
  public Role(String authority)
  {
	  this.authority=authority;
  }
  
  public Role(RoleView roleView)
  {
	  this.id= roleView.id();
	  this.authority= roleView.authority();
  }
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String authority;

}
