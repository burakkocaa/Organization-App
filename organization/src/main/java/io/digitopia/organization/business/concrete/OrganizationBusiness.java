package io.digitopia.organization.business.concrete;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.management.RuntimeErrorException;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.digitopia.organization.business.abstracts.OrganizationService;
import io.digitopia.organization.dataAccess.OrganizationRepository;
import io.digitopia.organization.entities.concretes.Organization;
import io.digitopia.organization.entities.concretes.User;
import io.digitopia.organization.entities.dtos.request.OrganizationCreateDto;
import io.digitopia.organization.entities.dtos.response.OrganizationResponseDto;
import io.digitopia.organization.entities.dtos.response.UserResponseDto;
import io.digitopia.organization.entities.results.DataResult;
import io.digitopia.organization.entities.results.Result;
import io.digitopia.organization.entities.results.SuccessDataResult;
import io.digitopia.organization.entities.results.SuccessResult;
import io.digitopia.organization.exception.NotNullException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrganizationBusiness implements OrganizationService {
	@Autowired
	private final OrganizationRepository organizationRepository;
	private final ModelMapper modelMapper;
	
	@Autowired
	private ObjectMapper mapper;

	@Override
	public Result delete(UUID userId) {
		this.organizationRepository.deleteById(userId);
		return new SuccessResult( "organization deleted"); 
	}

	@Override
	public DataResult<List<OrganizationResponseDto>> getAll() {
		List<Organization> organizations =this.organizationRepository.findAll();
		List<OrganizationResponseDto> organizationDtos = organizations.stream().map
		(organization->modelMapper.map(organization, OrganizationResponseDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<OrganizationResponseDto>>(organizationDtos, "organization listed");
	}	

	@Override
	public DataResult<OrganizationResponseDto> findByRegistryNumber(String registryNumber) {
		Organization organization =this.organizationRepository.findByRegistryNumber(registryNumber);
		OrganizationResponseDto organizationDtos = modelMapper.map(organization, OrganizationResponseDto.class);
		
		if (organization.getOrganizationName().equals("")) {
            throw new NotNullException("Registry number is not true ");
        }
		return new SuccessDataResult<OrganizationResponseDto>(organizationDtos,"organization listed");
	}

	@Override
	public DataResult<List<OrganizationResponseDto>> findAllByNormalizedName(String normalizedOrganizationName) {
		List<Organization> organizations = organizationRepository.findAllByNormalizedOrganizationName(normalizedOrganizationName);
		List<OrganizationResponseDto> organizationDtos = organizations.stream().map(organization->modelMapper.map
				(organization, OrganizationResponseDto.class)).collect(Collectors.toList());
		if (organizations.isEmpty()) {
            throw new NotNullException("Organization is not found");
        }
		return new SuccessDataResult<List<OrganizationResponseDto>>(organizationDtos, "organization listed");
	}

	@Override
	public DataResult<OrganizationCreateDto> add(@Valid OrganizationCreateDto organizationDto) {
		Organization organization =modelMapper.map(organizationDto, Organization.class);
		return new SuccessDataResult<OrganizationCreateDto>(modelMapper.map
				(this.organizationRepository.save(organization), OrganizationCreateDto.class), "organization added");
	}
	
	@Override
	public DataResult<List<User>> getUserByOrganizationId(UUID organizationId){
		Organization organization = getByOrganizationId(organizationId);
		System.out.println("asddasada"+organization);
		List<User> users = organization.getUser();
		return new SuccessDataResult<List<User>>(users, "Users is listed");
	}
	
	public Organization getByOrganizationId(UUID organizationId){
		return mapper.convertValue(organizationRepository.findById(organizationId)
				.orElseThrow(()->new RuntimeErrorException(null)),Organization.class); 
	}

}
