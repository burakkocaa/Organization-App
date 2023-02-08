package io.digitopia.organization.business.concrete;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.management.RuntimeErrorException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.digitopia.organization.business.abstracts.UserService;
import io.digitopia.organization.dataAccess.InvitationRepository;
import io.digitopia.organization.dataAccess.UserRepository;
import io.digitopia.organization.entities.concretes.Invitation;
import io.digitopia.organization.entities.concretes.InvitationStatus;
import io.digitopia.organization.entities.concretes.Organization;
import io.digitopia.organization.entities.concretes.User;
import io.digitopia.organization.entities.concretes.UserStatus;
import io.digitopia.organization.entities.dtos.request.UserCreateDto;
import io.digitopia.organization.entities.dtos.response.InvitationResponseDto;
import io.digitopia.organization.entities.dtos.response.OrganizationResponseDto;
import io.digitopia.organization.entities.dtos.response.UserResponseDto;
import io.digitopia.organization.entities.results.DataResult;
import io.digitopia.organization.entities.results.Result;
import io.digitopia.organization.entities.results.SuccessDataResult;
import io.digitopia.organization.entities.results.SuccessResult;
import io.digitopia.organization.exception.NotNullException;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.asm.Advice.This;

@Service
@RequiredArgsConstructor
public class UserBusiness implements UserService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private InvitationRepository invitationRepository;
	
	private final ModelMapper modelMapper;
	
	
	@Autowired
	private ObjectMapper mapper;
	
	public User getByUserId(UUID userId){
		return mapper.convertValue(userRepository.findById(userId)
		  .orElseThrow(()->new RuntimeErrorException(null)),User.class); 
	}

	@Override
	public DataResult<List<OrganizationResponseDto>> getOrganizationByUserId(UUID userId) {
		
		User user = getByUserId(userId);
		List<Organization> organizations = user.getOrganization();
		List<OrganizationResponseDto> organizationResponseDtos =organizations.stream().map
				(orgnz->modelMapper.map(orgnz, OrganizationResponseDto.class)).
				collect(Collectors.toList());
		
		return new SuccessDataResult<List<OrganizationResponseDto>>(organizationResponseDtos, "data listed");
	}

	@Override
	public DataResult<List<UserResponseDto>> findAllByNormalizedName(String normalizedName) {
		List<User> users =this.userRepository.findAllByNormalizedName(normalizedName);
		List<UserResponseDto> userDtos = users.stream().map
				(user->modelMapper.map(user, UserResponseDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<UserResponseDto>>(userDtos, "user listed");
	}

	@Override
	public DataResult<UserResponseDto> findByEmail(String email) {
		return new SuccessDataResult<UserResponseDto>(modelMapper.map
				(this.userRepository.findByEmail(email), UserResponseDto.class), "user listed");
	}

	@Override
	public DataResult<UserCreateDto> add(UserCreateDto userDto) {
		User user =modelMapper.map(userDto, User.class);
		user.setCreatedBy(user.getCreatedBy());
		user.setNormalizedName(userDto.getNormalizedName().toLowerCase());
		user.setCreatedAt(new Date());
		user.setStatus(UserStatus.ACTIVE);
		if (userDto.getNormalizedName().equals("string") || userDto.getNormalizedName().isEmpty()) {
            throw new NotNullException("User name must be not null");
        }
		return new SuccessDataResult<UserCreateDto>(modelMapper.map
				(this.userRepository.save(user), UserCreateDto.class), "data added");
	}

	@Override
	public Result delete(UUID userId) {
		this.userRepository.deleteById(userId);
		return new SuccessResult( "user deleted");
	}
	
	
	@Override
	public DataResult<InvitationResponseDto> update(InvitationResponseDto dto) {
		 Invitation invitation =this.findById(dto.getId());
		 invitation.setUpdatedAt(new Date());
		 invitation.setMessage(dto.getMessage());
		 invitation.setStatus(dto.getStatus());
		 Invitation inv=this.invitationRepository.save(invitation);
		 if(inv.getStatus().equals(InvitationStatus.ACCEPTED)) {
			 addUsersOrganization(inv);
		 }
		 
		 return new SuccessDataResult<InvitationResponseDto>(dto,"data updated");
		 }

	@Override
	public Invitation findById(UUID id) {
		return mapper.convertValue(invitationRepository.findById(id)
				.orElseThrow(()->new RuntimeErrorException(null)),Invitation.class);
	}
	

	
	private void addUsersOrganization(Invitation invitation){
		User user = invitation.getUser();
		user.getOrganization().add(invitation.getOrganization());
		userRepository.save(user);
	}
	
	
	

	
	
	
	
	
	
	
}
