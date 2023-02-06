package io.digitopia.organization.business.concrete;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.digitopia.organization.dataAccess.InvitationRepository;
import io.digitopia.organization.dataAccess.UserRepository;
import io.digitopia.organization.entities.concretes.Invitation;
import io.digitopia.organization.entities.concretes.User;

@Service
public class InvitationBusiness {
	
	@Autowired
	private InvitationRepository invitationRepository;
	
	@Autowired
	private ObjectMapper mapper;
	
	
	
	public void delete(UUID invitationId) {
		invitationRepository.deleteById(invitationId);
	}
	
	public Invitation add(Invitation invitation) {
		return invitationRepository.save(invitation);
	}
	
	public List<Invitation> getAll(){
		return invitationRepository.findAll();
	}



}
