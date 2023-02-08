package io.digitopia.organization.entities.dtos.request;

import java.util.Date;
import java.util.UUID;

import io.digitopia.organization.entities.concretes.InvitationStatus;
import io.digitopia.organization.entities.concretes.User;
import lombok.Data;

@Data
public class InvitationCreateDto {
	private String message;
	private UserInvitationDto user;
	private OrganizationInvitationDto organization;
}
