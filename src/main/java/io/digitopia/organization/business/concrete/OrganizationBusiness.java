package io.digitopia.organization.business.concrete;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.digitopia.organization.dataAccess.OrganizationRepository;
import io.digitopia.organization.entities.concretes.Organization;

@Service
public class OrganizationBusiness {
	@Autowired
	private OrganizationRepository organizationRepository;
	
	@Autowired
	private ObjectMapper mapper;
	
	public Organization add(Organization organization) {
		return organizationRepository.save(organization);
	}
	
	public void delete(UUID userId) {
		 organizationRepository.deleteById(userId);
		}
	
	public List<Organization> getAll(){
		return organizationRepository.findAll();
	}
	
	public Organization findByRegistryNumber(String registryNumber){
		return organizationRepository.findByRegistryNumber(registryNumber);
	}
	
	public List<Organization> findAllByNormalizedName(String normalizedOrganizationName){
		return organizationRepository.findAllByNormalizedOrganizationName(normalizedOrganizationName);
	}
}
