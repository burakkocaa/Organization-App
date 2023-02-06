package io.digitopia.organization.entities.dtos.response;

import java.util.Date;
import java.util.UUID;

import io.digitopia.organization.entities.dtos.request.UserCreateDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {

	private String fullName;
	private String email;
	private String normalizedName;
}
