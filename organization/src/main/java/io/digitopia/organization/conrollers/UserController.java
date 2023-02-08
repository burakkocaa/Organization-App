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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.digitopia.organization.business.abstracts.InvitationService;
import io.digitopia.organization.business.abstracts.UserService;
import io.digitopia.organization.business.concrete.UserBusiness;
import io.digitopia.organization.entities.concretes.Organization;
import io.digitopia.organization.entities.concretes.User;
import io.digitopia.organization.entities.dtos.request.UserCreateDto;
import io.digitopia.organization.entities.dtos.response.InvitationResponseDto;
import io.digitopia.organization.entities.dtos.response.OrganizationResponseDto;
import io.digitopia.organization.entities.dtos.response.UserResponseDto;
import io.digitopia.organization.entities.results.DataResult;
import io.digitopia.organization.entities.results.Result;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	
	private UserService userService;
	private InvitationService invitationService;
	
	@Autowired
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@GetMapping("/{normalizedName}/getAllByNormalizedName")
	public DataResult<List<UserResponseDto>> getAllByNormalizedName(@PathVariable("normalizedName") String normalizedName){
		return this.userService.findAllByNormalizedName(normalizedName);
	}
	
	@PostMapping("/add")
	public DataResult<UserCreateDto> add(@Valid @RequestBody UserCreateDto user) {
		return this.userService.add(user);
	}
	
	@GetMapping("/{email}/getAllUserByEmail" )
	public DataResult<UserResponseDto> getUserByEmail(@PathVariable("email") String email){
		return this.userService.findByEmail(email);
	}
	
	@DeleteMapping("/{id}")
	  public Result deleteById(@PathVariable UUID id) {
		return userService.delete(id);
	  }
	
	@PutMapping("/put")
	public DataResult<InvitationResponseDto> invitationProcesses( @RequestBody InvitationResponseDto dto){
		return userService.update( dto);
	}
	
	@GetMapping("/{userId}/getAllOrganization" )
	public DataResult<List<OrganizationResponseDto>> getAllOrganization(@PathVariable("userId") UUID userId){
		return this.userService.getOrganizationByUserId(userId);
	}

}
