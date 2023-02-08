package io.digitopia.organization.business.concrete;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException.BadRequest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Supplier;

import io.digitopia.organization.business.abstracts.InvitationService;
import io.digitopia.organization.dataAccess.InvitationRepository;
import io.digitopia.organization.dataAccess.UserRepository;
import io.digitopia.organization.entities.concretes.Invitation;
import io.digitopia.organization.entities.concretes.InvitationStatus;
import io.digitopia.organization.entities.concretes.Organization;
import io.digitopia.organization.entities.concretes.User;
import io.digitopia.organization.entities.dtos.request.InvitationCreateDto;
import io.digitopia.organization.entities.dtos.response.InvitationResponseDto;
import io.digitopia.organization.entities.dtos.response.OrganizationResponseDto;
import io.digitopia.organization.entities.results.DataResult;
import io.digitopia.organization.entities.results.Result;
import io.digitopia.organization.entities.results.SuccessDataResult;
import io.digitopia.organization.entities.results.SuccessResult;
import io.digitopia.organization.exception.BadRequestException;
import io.digitopia.organization.exception.NotFoundException;
import io.digitopia.organization.exception.NotNullException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InvitationBusiness implements InvitationService {
	
	@Autowired
	private InvitationRepository invitationRepository;
	
	private final ModelMapper modelMapper;
	
	@Autowired
	private ObjectMapper mapper;
	
	
	private static final Supplier<BadRequestException> userHasPengingInvitationRequestExceptionSupplier =
            () -> new BadRequestException("User has a pending invitation request !");
            
	public Result delete(UUID invitationId) {
		this.invitationRepository.deleteById(invitationId);
		return new SuccessResult( "invitation deleted");
	}
	
	public DataResult<InvitationCreateDto> add(InvitationCreateDto invitationDto) {
		Invitation invitation =modelMapper.map(invitationDto, Invitation.class);
		invitation.setStatus(InvitationStatus.PENDING);
		invitation.setCreatedAt(new Date());
		
		List<Invitation> inv = getInvitationByUserId(invitationDto.getUser().getId());
		List<Invitation> filteredInvitation=inv.stream().filter(invit->invit.getStatus().equals(InvitationStatus.PENDING))
		.collect(Collectors.toList());
		
		if(filteredInvitation.size()>0) {
			throw userHasPengingInvitationRequestExceptionSupplier.get();
		}
		
		if (invitationDto.getMessage().equals("string") || invitationDto.getMessage().isEmpty()) {
            throw new NotNullException("Invitation name must be not null");
        }
		return new SuccessDataResult<InvitationCreateDto>(modelMapper.map
				(this.invitationRepository.save(invitation), InvitationCreateDto.class), "invitation added");
	}
	
	public DataResult<List<InvitationResponseDto>> getAll(){
		
		List<Invitation> invitations =this.invitationRepository.findAll();
		List<InvitationResponseDto>invitationDtos = invitations.stream().map
				(invitation->modelMapper.map(invitation, InvitationResponseDto.class)).collect(Collectors.toList());
		
		
		if (invitations.isEmpty()) {
            throw new NotFoundException("Invitation is not found");
        }
		return new SuccessDataResult<List<InvitationResponseDto>>(invitationDtos,"invitation listed");
	}

	@Override
	public DataResult<InvitationResponseDto> getById(UUID id) {
		Optional<Invitation> invitation =this.invitationRepository.findById(id);
		return new SuccessDataResult<InvitationResponseDto>(modelMapper.map(invitation, InvitationResponseDto.class), "data listed");
	}
	
	public List<Invitation> getInvitationByUserId(UUID userId){
		List<Invitation> invitations = invitationRepository.getInvitationByUserId(userId);
		return invitations;
	}



}
