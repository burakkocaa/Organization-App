package io.digitopia.organization.business.abstracts;

import java.util.List;
import java.util.UUID;

import io.digitopia.organization.entities.concretes.Invitation;
import io.digitopia.organization.entities.concretes.Organization;
import io.digitopia.organization.entities.concretes.User;
import io.digitopia.organization.entities.dtos.request.UserCreateDto;
import io.digitopia.organization.entities.dtos.response.InvitationResponseDto;
import io.digitopia.organization.entities.dtos.response.OrganizationResponseDto;
import io.digitopia.organization.entities.dtos.response.UserResponseDto;
import io.digitopia.organization.entities.results.DataResult;
import io.digitopia.organization.entities.results.Result;

public interface UserService {
	DataResult<List<OrganizationResponseDto>> getOrganizationByUserId(UUID userId);
	
	DataResult<List<UserResponseDto>> findAllByNormalizedName(String normalizedName);
	
	DataResult<UserResponseDto> findByEmail(String email);
	
	DataResult<UserCreateDto> add(UserCreateDto user);
	
	Result delete(UUID userId);
	
	DataResult<InvitationResponseDto>update(InvitationResponseDto dto);
	
	Invitation findById(UUID id);


}
