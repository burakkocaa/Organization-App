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
import io.digitopia.organization.entities.dtos.request.InvitationCreateDto;
import io.digitopia.organization.entities.dtos.response.InvitationResponseDto;
import io.digitopia.organization.entities.results.DataResult;
import io.digitopia.organization.entities.results.Result;

@RestController
@RequestMapping("/api/invitation")
public class InvitationController {
	@Autowired
	private InvitationBusiness invitationBusiness;
	
	@PostMapping("/add")
	public DataResult<InvitationCreateDto> add(@Valid @RequestBody InvitationCreateDto invitation) {
		return this.invitationBusiness.add(invitation);
	}
	
	@DeleteMapping("/{id}")
	  public Result deleteById(@PathVariable UUID id) {
	    return invitationBusiness.delete(id);
	  }

	@GetMapping("/getAll" )
	public DataResult<List<InvitationResponseDto>> getAll(){
		return this.invitationBusiness.getAll();
	}
	

}
