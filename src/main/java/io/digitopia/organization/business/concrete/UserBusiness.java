package io.digitopia.organization.business.concrete;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.digitopia.organization.dataAccess.UserRepository;
import io.digitopia.organization.entities.concretes.Organization;
import io.digitopia.organization.entities.concretes.User;
import io.digitopia.organization.entities.dtos.request.UserCreateDto;
import io.digitopia.organization.entities.dtos.response.UserResponseDto;

@Service
public class UserBusiness {
	@Autowired
	private UserRepository userRepository;
	
	
	@Autowired
	private ObjectMapper mapper;
	
	public User getByUserId(UUID userId){
		//.orElseThrow(() -> new ConfigDataResourceNotFoundException("Couldn't found user: " + userId.toString()))
		return mapper.convertValue(userRepository.findById(userId),User.class); 
	}
	
	public List<Organization> getOrganizationByUserId(UUID userId){
		User user = getByUserId(userId);
		List<Organization> organizations = user.getOrganization();
		return organizations;
	}
	
	public List<User> findAllByNormalizedName(String normalizedName){
		return userRepository.findAllByNormalizedName(normalizedName);
	}
	
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	public UserResponseDto add(UserCreateDto userRequest){
		
	  User user = new User(userRequest.getCreatedAt(),userRequest.getCreatedBy(),userRequest.getFullName(),userRequest.getEmail(),userRequest.getNormalizedName(),userRequest.getOrganizations());
	  User user1= userRepository.save(user);
	  UserResponseDto dto = new UserResponseDto(user1.getEmail(),user1.getFullName(),user1.getNormalizedName());
	  return dto;
	}
	
	public void delete(UUID userId) {
	 userRepository.deleteById(userId);
	}

	
	
	
	
	
	
	
	
}
