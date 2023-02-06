package io.digitopia.organization.dataAccess;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.digitopia.organization.entities.concretes.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID>{
	
	//@Query("Select u From User u Where u.normalizedName = :normalizedName")
	List<User> findAllByNormalizedName(String normalizedName);
	
	User findByEmail(String email);
	
}
