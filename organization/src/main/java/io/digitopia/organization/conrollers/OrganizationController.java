package io.digitopia.organization.conrollers;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.digitopia.organization.business.abstracts.OrganizationService;
import io.digitopia.organization.business.concrete.OrganizationBusiness;
import io.digitopia.organization.entities.concretes.Organization;
import io.digitopia.organization.entities.concretes.User;
import io.digitopia.organization.entities.dtos.request.OrganizationCreateDto;
import io.digitopia.organization.entities.dtos.response.OrganizationResponseDto;
import io.digitopia.organization.entities.results.DataResult;
import io.digitopia.organization.entities.results.Result;

@RestController
@RequestMapping("/api/organization")
public class OrganizationController {
	
	@Autowired
	private OrganizationService organizationService;
	
	@PostMapping("/add")
	public DataResult<OrganizationCreateDto> add(@Valid @RequestBody OrganizationCreateDto organization) {
		return this.organizationService.add(organization);
	}
	
	@DeleteMapping("/{id}")
	  public Result deleteById(@PathVariable UUID id) {
		
	    return organizationService.delete(id);
	  }

	@GetMapping("/getAll" )
	public DataResult<List<OrganizationResponseDto>> getAll(){
		return this.organizationService.getAll();
	}
	
	@GetMapping("/{normalizedName}/getAllOrganizationByNormalizedName" )
	public DataResult<List<OrganizationResponseDto>> getAllOrganizationByNormalizedName(@PathVariable("normalizedName") String normalizedName){
		return this.organizationService.findAllByNormalizedName(normalizedName);
	}
	
	@GetMapping("/{registryNumber}/getOrganizationByRegistryNumber" )
	public DataResult<OrganizationResponseDto> getOrganizationByRegistryNumber(@PathVariable("registryNumber") String number){
		return this.organizationService.findByRegistryNumber(number);
	}
	@GetMapping("/{organizationId}/getAllUser" )
	public DataResult<List<User>> getAllUser(@PathVariable("organizationId") UUID organizationId){
		return this.organizationService.getUserByOrganizationId(organizationId);
	}

}
