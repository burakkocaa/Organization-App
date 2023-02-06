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

import io.digitopia.organization.business.concrete.InvitationBusiness;
import io.digitopia.organization.entities.concretes.Invitation;

@RestController
@RequestMapping("/api/invitation")
public class InvitationController {
	@Autowired
	private InvitationBusiness invitationBusiness;
	
	@PostMapping("/add")
	public ResponseEntity<Invitation> add(@Valid @RequestBody Invitation invitation) {
		return ResponseEntity.ok(this.invitationBusiness.add(invitation));
	}
	
	@DeleteMapping("/{id}")
	  public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
	    invitationBusiness.delete(id);
	    return ResponseEntity.noContent().build();
	  }

	@GetMapping("/getAll" )
	public ResponseEntity<List<Invitation>> getAll(){
		return ResponseEntity.ok(this.invitationBusiness.getAll());
	}
	

}
