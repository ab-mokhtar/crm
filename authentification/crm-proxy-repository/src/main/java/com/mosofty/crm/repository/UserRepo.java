package com.mosofty.crm.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.mosofty.crm.exception.NotFoundException;
import com.mosofty.crm.model.User;

@Repository
@CacheConfig(cacheNames = "users")
public interface UserRepo extends PagingAndSortingRepository<User, Long> {

	@CacheEvict(allEntries = true)
	<S extends User> List<S> saveAll(Iterable<S> entities);

	@Caching(evict = { @CacheEvict(key = "#p0.id", condition = "#p0.id != null"),
			@CacheEvict(key = "#p0.username", condition = "#p0.username != null") })
	<S extends User> S save(S entity);

	@Cacheable
	Optional<User> findById(String objectId);

	@Cacheable
	default User getById(Long id) {
		var optionalUser = findById(id);
		if (optionalUser.isEmpty()) {
			throw new NotFoundException(User.class, id);
		}
		if (!optionalUser.get().isEnabled()) {
			throw new NotFoundException(User.class, id);
		}
		return optionalUser.get();
	}

	@Cacheable
	Optional<User> findByUsername(String username);
	
	 
	List<User> findByUsernameOrFullNameOrId(String userName,String fullName, Long id);
	
    List<User> findAll();

}
