package io.digitopia.organization.entities.dtos.response;

import java.util.Date;
import java.util.UUID;

import io.digitopia.organization.entities.concretes.InvitationStatus;
import lombok.Data;

@Data
public class InvitationResponseDto {
	private String message;
	private InvitationStatus status;
	private UUID id;

}
