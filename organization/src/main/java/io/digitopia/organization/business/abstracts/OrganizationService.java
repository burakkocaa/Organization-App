package io.digitopia.organization.business.abstracts;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.RequestBody;

import io.digitopia.organization.entities.concretes.Organization;
import io.digitopia.organization.entities.concretes.User;
import io.digitopia.organization.entities.dtos.request.OrganizationCreateDto;
import io.digitopia.organization.entities.dtos.response.OrganizationResponseDto;
import io.digitopia.organization.entities.results.DataResult;
import io.digitopia.organization.entities.results.Result;

public interface OrganizationService {

	 Result delete(UUID userId);
	 
	 DataResult<OrganizationCreateDto> add(@Valid @RequestBody OrganizationCreateDto organizationDto);
	
	 DataResult<List<OrganizationResponseDto>> getAll();
	
	 DataResult<OrganizationResponseDto> findByRegistryNumber(String registryNumber);
	
	 DataResult<List<OrganizationResponseDto>> findAllByNormalizedName(String normalizedOrganizationName);
	 
	 DataResult<List<User>> getUserByOrganizationId(UUID userId);
}
