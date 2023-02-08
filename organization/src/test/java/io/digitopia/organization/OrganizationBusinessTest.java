package io.digitopia.organization;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import io.digitopia.organization.business.concrete.OrganizationBusiness;
import io.digitopia.organization.dataAccess.OrganizationRepository;
import io.digitopia.organization.entities.concretes.Organization;
import io.digitopia.organization.entities.dtos.request.OrganizationCreateDto;
import io.digitopia.organization.entities.dtos.response.OrganizationResponseDto;
import io.digitopia.organization.entities.results.DataResult;
import io.digitopia.organization.entities.results.Result;
import io.digitopia.organization.entities.results.SuccessDataResult;
import io.digitopia.organization.exception.NotNullException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class OrganizationBusinessTest {

  @Mock
  private OrganizationRepository organizationRepository;

  @Mock
  private ModelMapper modelMapper;

  @InjectMocks
  private OrganizationBusiness organizationBusiness;

  @Test
  public void whenGetAll() {
	  Organization organization1 = new Organization();
      organization1.setId(UUID.randomUUID());
      organization1.setRegistryNumber("Organization1");

      Organization organization2 = new Organization();
      organization2.setId(UUID.randomUUID());
      organization2.setRegistryNumber("Organization2");

      List<Organization> organizations = Arrays.asList(organization1, organization2);
      when(organizationRepository.findAll()).thenReturn(organizations);

      OrganizationResponseDto organizationResponseDto1 = new OrganizationResponseDto();
      organizationResponseDto1.setId(UUID.randomUUID());
      organizationResponseDto1.setRegistryNumber("Organization21");

      OrganizationResponseDto organizationResponseDto2 = new OrganizationResponseDto();
      organizationResponseDto2.setId(UUID.randomUUID());
      organizationResponseDto2.setRegistryNumber("Organization122");

      when(modelMapper.map(organization1, OrganizationResponseDto.class)).thenReturn(organizationResponseDto1);
      when(modelMapper.map(organization2, OrganizationResponseDto.class)).thenReturn(organizationResponseDto2);

      DataResult<List<OrganizationResponseDto>> result = organizationBusiness.getAll();

      assertEquals("organization listed", result.getMessage());
      assertEquals(Arrays.asList(organizationResponseDto1, organizationResponseDto2), result.getData());
  }
  
  	@Test
  	public void whenGetAllByRegistryNumber() {
	  
	  String registryNumber = "123456789";

      Organization organization = new Organization();
      organization.setId(UUID.randomUUID());
      organization.setRegistryNumber(registryNumber);
      organization.setOrganizationName("Organization 1");

      when(organizationRepository.findByRegistryNumber(registryNumber)).thenReturn(organization);

      OrganizationResponseDto organizationResponseDto = new OrganizationResponseDto();
      organizationResponseDto.setId(UUID.randomUUID());
      organizationResponseDto.setRegistryNumber(registryNumber);
      organizationResponseDto.setOrganizationName("Organization 2");

      when(modelMapper.map(organization, OrganizationResponseDto.class)).thenReturn(organizationResponseDto);

      DataResult<OrganizationResponseDto> result = organizationBusiness.findByRegistryNumber(registryNumber);

      assertEquals("organization listed", result.getMessage());
      assertEquals(organizationResponseDto, result.getData());
  }
  
  @Test
  public void whenCreateOrganization() {
	  OrganizationCreateDto organizationCreateDto = new OrganizationCreateDto();
      organizationCreateDto.setOrganizationName("Organization 1");
      organizationCreateDto.setRegistryNumber("123456789");

      Organization organization = new Organization();
      organization.setId(UUID.randomUUID());
      organization.setRegistryNumber(organizationCreateDto.getRegistryNumber());
      organization.setOrganizationName(organizationCreateDto.getOrganizationName());
      
      when(modelMapper.map(organizationCreateDto, Organization.class)).thenReturn(organization);
      when(organizationRepository.save(organization)).thenReturn(organization);
      when(modelMapper.map(organization, OrganizationCreateDto.class)).thenReturn(organizationCreateDto);
      
      DataResult<OrganizationCreateDto> result = organizationBusiness.add(organizationCreateDto);
      
      assertEquals("organization added", result.getMessage());
      assertEquals(organizationCreateDto, result.getData());
  }
  
  @Test
  public void whenDeleteById() {
	  UUID userId = UUID.randomUUID();
	 
	  doNothing().when(organizationRepository).deleteById(userId);
	  
	  Result result = organizationBusiness.delete(userId);
      assertEquals("organization deleted", result.getMessage());
  }
  
  
}
