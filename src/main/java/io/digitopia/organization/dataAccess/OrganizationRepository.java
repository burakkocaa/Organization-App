package io.digitopia.organization.dataAccess;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.digitopia.organization.entities.concretes.Organization;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, UUID> {
	
List<Organization> findAllByNormalizedOrganizationName(String normalizedOrganizationName);
	
Organization findByRegistryNumber(String registryNumber);
}
