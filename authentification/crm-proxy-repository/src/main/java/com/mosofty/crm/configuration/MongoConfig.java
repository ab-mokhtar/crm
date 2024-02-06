package com.mosofty.crm.configuration;

import static java.util.Optional.ofNullable;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.context.SecurityContextHolder;

import com.mosofty.crm.model.User;

@Configuration
@EnableJpaAuditing
public class MongoConfig {

	@Bean
	public AuditorAware<Long> auditorProvider() {
		return () -> {
			var authentication = SecurityContextHolder.getContext().getAuthentication();
			User user = null;
			if (authentication != null && authentication.getPrincipal() instanceof User) {
				user = (User) authentication.getPrincipal();
			}
			return ofNullable(user).map(User::getId);
		};
	}
}
