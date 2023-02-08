package io.digitopia.organization.entities.dtos.request;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrganizationCreateDto {
	private String organizationName;
	private String registryNumber;
	private String normalizedOrganizationName;
	private String contactEmail;
	private int yearFounded;
	private String phone;
    private int companySize;
}
