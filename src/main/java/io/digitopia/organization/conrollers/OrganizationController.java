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

import io.digitopia.organization.business.concrete.OrganizationBusiness;
import io.digitopia.organization.entities.concretes.Organization;

@RestController
@RequestMapping("/api/organization")
public class OrganizationController {
	
	@Autowired
	private OrganizationBusiness organizationBusiness;
	
	@PostMapping("/add")
	public ResponseEntity<Organization> add(@Valid @RequestBody Organization organization) {
		return ResponseEntity.ok(this.organizationBusiness.add(organization));
	}
	
	@DeleteMapping("/{id}")
	  public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
	    organizationBusiness.delete(id);
	    return ResponseEntity.noContent().build();
	  }


	@GetMapping("/getAll" )
	public ResponseEntity<List<Organization>> getAll(){
		return ResponseEntity.ok(this.organizationBusiness.getAll());
	}
	
	@GetMapping("/{normalizedName}/getAllOrganizationByNormalizedName" )
	public ResponseEntity<List<Organization>> getAllOrganizationByNormalizedName(@PathVariable("normalizedName") String normalizedName){
		return ResponseEntity.ok(this.organizationBusiness.findAllByNormalizedName(normalizedName));
	}
	
	@GetMapping("/{registryNumber}/getOrganizationByNormalizedName" )
	public ResponseEntity<Organization> getOrganizationByNormalizedName(@PathVariable("registryNumber") String number){
		return ResponseEntity.ok(this.organizationBusiness.findByRegistryNumber(number));
	}

}
