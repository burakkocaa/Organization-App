package io.digitopia.organization.entities.dtos.response;

import java.util.Date;
import java.util.UUID;

import lombok.Data;

@Data
public class OrganizationResponseDto {
	private UUID id;
	private String organizationName;
	private String registryNumber;
	private String normalizedOrganizationName;
	private String contactEmail;
	private int yearFounded;
	private String phone;
    private int companySize;
    private Date createdAt;
}
