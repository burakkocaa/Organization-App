package io.digitopia.organization.entities.dtos.request;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import io.digitopia.organization.entities.concretes.Organization;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateDto {
	
	private String fullName;
	private String email;
	private String normalizedName;
}
