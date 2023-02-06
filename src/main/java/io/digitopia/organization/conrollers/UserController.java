package io.digitopia.organization.conrollers;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.digitopia.organization.business.concrete.UserBusiness;
import io.digitopia.organization.entities.concretes.Organization;
import io.digitopia.organization.entities.concretes.User;
import io.digitopia.organization.entities.dtos.request.UserCreateDto;
import io.digitopia.organization.entities.dtos.response.UserResponseDto;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private UserBusiness userBusiness;
	
	@GetMapping("/getAllByNormalizedName")
	public ResponseEntity<List<User>> getAllByNormalizedName(@RequestParam("normalizedName") String normalizedName){
		List<User> users = userBusiness.findAllByNormalizedName(normalizedName);
		return new ResponseEntity<List<User>>(users,HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public ResponseEntity<UserResponseDto> add(@Valid @RequestBody UserCreateDto user) {
		return ResponseEntity.ok(this.userBusiness.add(user));
	}
	
	@GetMapping("/{userId}/getAllOrganization" )
	public ResponseEntity<List<Organization>> getAllOrganization(@PathVariable("userId") UUID userId){
		return ResponseEntity.ok(this.userBusiness.getOrganizationByUserId(userId));
	}
	@GetMapping("/{userName}/getAllUserByNormalizedName" )
	public ResponseEntity<List<User>> getAllUserByNormalizedName(@PathVariable("userName") String userName){
		return ResponseEntity.ok(this.userBusiness.findAllByNormalizedName(userName));
	}
	@GetMapping("/{email}/getAllUserByEmail" )
	public ResponseEntity<User> getUserByEmail(@PathVariable("email") String email){
		return ResponseEntity.ok(this.userBusiness.findByEmail(email));
	}
	@DeleteMapping("/{id}")
	  public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
	    userBusiness.delete(id);
	    return ResponseEntity.noContent().build();
	  }
	
}
